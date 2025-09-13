package medium.task_2749;


/**
 * LeetCode — 2749. Minimum Operations to Make the Integer Zero
 * <br><br>
 * You are given two integers {@code num1} and {@code num2}. In one operation, choose an integer {@code i} in the range
 * [0, 60] and subtract {@code (2^i + num2)} from {@code num1}.<br> Return the minimum number of operations needed to
 * make {@code num1} equal to 0. If it is impossible, return -1.
 *
 * <br><b>Examples:</b>
 * <pre>
 * Example 1:
 * Input:  num1 = 3, num2 = -2
 * Output: 3
 * Explanation:
 *   i = 2:  3 - (2^2 + (-2)) = 3 - (4 - 2) = 1
 *   i = 2:  1 - (2^2 + (-2)) = 1 - (4 - 2) = -1
 *   i = 0: -1 - (2^0 + (-2)) = -1 - (1 - 2) = 0
 *
 * Example 2:
 * Input:  num1 = 5, num2 = 7
 * Output: -1
 * </pre>
 *
 * <b>Constraints:</b><br>
 * - 1 <= num1 <= 1e9<br> - -1e9 <= num2 <= 1e9<br> - On each operation, i ∈ [0, 60]<br>
 * <br>
 * <b>Link:</b><br>
 * <a href="https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/description/">
 * https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero/description/
 * </a>
 *
 * @param num1 starting integer
 * @param num2 offset integer used in each operation
 * @return minimum number of operations to reach 0, or -1 if impossible
 */
public class Task2749 {
    public static void main(String[] args) {
        System.out.println(makeTheIntegerZero(3, -2));
        System.out.println(makeTheIntegerZero(5, 7));
        System.out.println(makeTheIntegerZero(-1, 0));
        System.out.println(makeTheIntegerZero(21, 0));

    }

    public static int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k < 60; k++) {
            int s = num1 - k * num2;

            if (s < 0) continue;
            if (k >s) continue;
            if(popcount(s) <=k ) return k;
        }
        return -1;
    }

    private static int popcount(int num) {
        int count = 0;
        for (char c : Integer.toBinaryString(num).toCharArray()) {
            if (c == '1') count++;
        }
        return count;
    }
}
