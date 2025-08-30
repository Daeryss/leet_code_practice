package medium.task_32;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode — 36. Valid Sudoku
 * <br><br>
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to:
 * <br>
 * 1) Each row must contain the digits 1-9 without repetition.<br> 2) Each column must contain the digits 1-9 without
 * repetition.<br> 3) Each of the nine 3x3 sub-boxes must contain the digits 1-9 without repetition.<br>
 * <br>
 * Notes:<br> - A partially filled board can be valid even if it is not solvable.<br> - Only non-empty cells (digits
 * '1'..'9') are validated; '.' denotes empty.<br>
 * <br>
 * <b>Input format:</b> 9x9 char matrix where each entry is '1'..'9' or '.'.<br>
 * <br>
 * <b>Examples:</b>
 * <pre>
 * Example 1:
 * Input:
 * [["5","3",".",".","7",".",".",".","."],
 *  ["6",".",".","1","9","5",".",".","."],
 *  [".","9","8",".",".",".",".","6","."],
 *  ["8",".",".",".","6",".",".",".","3"],
 *  ["4",".",".","8",".","3",".",".","1"],
 *  ["7",".",".",".","2",".",".",".","6"],
 *  [".","6",".",".",".",".","2","8","."],
 *  [".",".",".","4","1","9",".",".","5"],
 *  [".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 * (Same as Example 1, but top-left '5' changed to '8')
 * Output: false
 * Explanation: Two '8's appear in the top-left 3x3 box.
 * </pre>
 *
 * <b>Constraints:</b><br>
 * - board.length == 9<br> - board[i].length == 9<br> - board[i][j] ∈ {'1'..'9', '.'}<br>
 * <br>
 * <b>Link:</b><br>
 * <a href="https://leetcode.com/problems/valid-sudoku/description">
 * https://leetcode.com/problems/valid-sudoku/description
 * </a>
 *
 * @param board 9x9 поле судоку; цифры '1'..'9' и '.' как пустая ячейка
 * @return true — если текущее заполнение не нарушает правил по строкам/столбцам/блокам 3x3; иначе false
 */
public class Task32 {

    public static void main(String[] args) {
        // Пример 1 из условия
        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // Пример 2 из условия (тот же, но верхний левый элемент заменён на '8')
        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        boolean result1 = isValidSudoku(board1);
        boolean result2 = isValidSudoku(board2);

        System.out.println(result1 == true
                ? "[OK] Example 1 → true"
                : "[FAIL] Example 1 → got " + result1 + ", expected true");

        System.out.println(result2 == false
                ? "[OK] Example 2 → false"
                : "[FAIL] Example 2 → got " + result2 + ", expected false");
    }

    public static boolean isValidSudoku(char[][] board) {
        int startDecimalIndex = '0';
        int endDecimalIndex = '9';

        for (char[] chars : board) {

            Set<Character> innerSet = new HashSet<>();
            int counter = 0;

            for (char c : chars) {
                if (c == '-') continue;

                if (c >= startDecimalIndex && c <= endDecimalIndex) {
                    innerSet.add(c);
                    counter++;
                }
            }
            if (innerSet.size() != counter) {
                System.out.println("incorrect in line");
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            Set<Character> innerSet = new HashSet<>();
            int counter = 0;

            for (int j = 0; j < board.length; j++) {
                char c = board[j][i];
                if (c == '.') continue;

                if (c >= startDecimalIndex && c <= endDecimalIndex) {
                    innerSet.add(c);
                    counter++;
                }
            }
            if (innerSet.size() != counter) {
                System.out.println("incorrect in col");
                return false;
            }
        }


        for (int mainRow = 0; mainRow < 9; mainRow += 3) {
            for (int mainCol = 0; mainCol < 9; mainCol += 3) {
                if (!isValidBlock(board, mainRow, mainCol)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidBlock(char[][] board, int rowIndex, int colIndex) {
        int startDecimalIndex = '0';
        int endDecimalIndex = '9';

        Set<Character> innerSet = new HashSet<>();
        int counter = 0;
        for (int r = rowIndex; r < rowIndex + 3; r++) {
            for (int c = colIndex; c < colIndex + 3; c++) {
                char cell = board[r][c];
                if (cell == '.') continue;

                if (cell >= startDecimalIndex && cell <= endDecimalIndex) {
                    innerSet.add(cell);
                    counter++;
                }
            }
        }

        if (innerSet.size() != counter) {
            System.out.printf("incorrect in block");
            return false;
        }

        return true;
    }

    public static void printBlocks(char[][] board) {
        int N = board.length;          // всегда 9
        int k = (int) Math.sqrt(N);    // размер блока (3)

        for (int i = 0; i < N; i++) {
            // Горизонтальный разделитель между блоками по строкам
            if (i != 0 && i % k == 0) {
                for (int t = 0; t < N * 2 + k - 1 + 3; t++) {
                    System.out.print("-");
                }
                System.out.println();
            }

            for (int j = 0; j < N; j++) {
                // Вертикальный разделитель между блоками по столбцам
                if (j != 0 && j % k == 0) {
                    System.out.print(" | ");
                }
                System.out.print( board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
