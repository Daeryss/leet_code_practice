package medium.task_869;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode — 869. Reordered Power of 2
 * <br><br>
 * Given an integer {@code n}, you may reorder its digits in any order (including the original),
 * with the restriction that the leading digit cannot be zero. Return {@code true} iff the digits
 * can be reordered to form a power of two.
 *
 * <br><b>Examples:</b>
 * <pre>
 * Example 1:
 * Input:  n = 1
 * Output: true
 *
 * Example 2:
 * Input:  n = 10
 * Output: false
 * </pre>
 *
 * <b>Constraints:</b><br>
 * 1 <= n <= 1e9
 *
 * <br><b>Link:</b><br>
 * <a href="https://leetcode.com/problems/reordered-power-of-2/">
 *   https://leetcode.com/problems/reordered-power-of-2/
 * </a>
 *
 * @param n input integer
 * @return {@code true} if some permutation of {@code n}'s digits (without leading zero) is a power of two; otherwise {@code false}
 */
public class Task869 {
    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(218));
    }

    public static boolean reorderedPowerOf2(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);

        return generatePowers(chars.length).contains(new String(chars));
    }

    private static Set<String> generatePowers(int length) {
        Set<String> numbers = new HashSet<>();
        int maxPow = 30;

        for (int i = 0; i < maxPow; i++) {
            char[] charsNum = String.valueOf((long)Math.pow(2, i)).toCharArray();
            Arrays.sort(charsNum);

            if (charsNum.length == length) {
                numbers.add(new String(charsNum));
            } if (charsNum.length > length) {
                break;
            }
        }

        return numbers;
    }
}
