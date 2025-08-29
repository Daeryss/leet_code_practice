package medium.task_3021;

/**
 * LeetCode — Alice and Bob Playing Flower Game
 * <br>
 * <br>
 * Alice and Bob are playing a turn-based game on a field with two lanes of flowers between them.<br>
 * There are x flowers in the first lane and y flowers in the second lane.<br>
 * <br>
 * <b>Rules:</b><br>
 * 1) Alice takes the first turn.<br>
 * 2) On each turn, a player must choose one of the two lanes and pick exactly one flower from that lane.<br>
 * 3) After a turn, if no flowers remain at all, the current player captures the opponent and wins the game.<br>
 * <br>
 * <b>Task:</b><br>
 * Given two integers <code>n</code> and <code>m</code>, compute the number of pairs (x, y) such that:<br>
 * &nbsp;&nbsp;- 1 <= x <= n<br>
 * &nbsp;&nbsp;- 1 <= y <= m<br>
 * &nbsp;&nbsp;- Alice wins the game according to the rules above when starting from (x, y).<br>
 * <br>
 * <b>Return:</b> the number of such pairs.<br>
 * <br>
 * <b>Examples:</b><br>
 * <pre>
 * Input:  n = 3, m = 2
 * Output: 3
 * Explanation: Valid pairs are (1,2), (3,2), (2,1).
 *
 * Input:  n = 1, m = 1
 * Output: 0
 * Explanation: No pairs satisfy the conditions.
 * </pre>
 *
 * <b>Constraints:</b><br>
 * 1 <= n, m <= 1e5<br>
 * <br>
 * <b>Link:</b><br>
 * <a href="https://leetcode.com/problems/alice-and-bob-playing-flower-game/description/">
 *     https://leetcode.com/problems/alice-and-bob-playing-flower-game/description/
 * </a>
 * <br>
 * <b>Note:</b><br>
 * The result may exceed 32-bit signed integer range; prefer a 64-bit integer type for the return value.
 */

public class Task3021 {

    public static void main(String[] args) {
        int[][] cases = {
                {3, 2, 3},
                {1, 1, 0},
                {2, 2, 2},
                {3, 3, 9},
                {4, 4, 8}
        };

        for (int[] c : cases) {
            int n = c[0], m = c[1];
            long expected = c[2];
            long result = flowerGame(n, m);

            if (result == expected) {
                System.out.println("[OK] n=" + n + ", m=" + m + " → " + result);
            } else {
                System.out.println("[FAIL] n=" + n + ", m=" + m +
                        " → got " + result + ", expected " + expected);
            }
        }
    }

    public static long flowerGame(int n, int m) {
        long evensN = n / 2;
        long oddsN = n % 2 == 0 ? n /2 : n / 2 + 1;
        long evensM = m / 2;
        long oddsM = m % 2 == 0 ? m /2 : m / 2 + 1;


        return (evensN * oddsM + oddsN * evensM);
    }
}
