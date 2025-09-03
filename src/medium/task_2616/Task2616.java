package medium.task_2616;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode — 2616. Minimize the Maximum Difference of Pairs
 * <br><br>
 * You are given a 0-indexed integer array {@code nums} and an integer {@code p}.<br> Find {@code p} disjoint pairs of
 * indices (no index reused) such that the
 * <b>maximum</b> absolute difference among all chosen pairs is minimized.<br>
 * The difference of a pair (i, j) is {@code |nums[i] - nums[j]|}.<br> The maximum of an empty set is defined as 0
 * (i.e., if {@code p == 0}).<br>
 * <br>
 * <b>Return:</b> the minimum possible value of the maximum difference among all {@code p} pairs.<br>
 * <br>
 * <b>Examples:</b>
 * <pre>
 * Example 1:
 * Input:  nums = [10,1,2,7,1,3], p = 2
 * Output: 1
 * Explanation:
 *   Pairs can be (1,4) and (2,5):
 *   max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1 → answer = 1.
 *
 * Example 2:
 * Input:  nums = [4,2,1,2], p = 1
 * Output: 0
 * Explanation:
 *   Pair (1,3) has difference |2 - 2| = 0, which is minimal.
 * </pre>
 *
 * <b>Constraints:</b><br>
 * - 1 <= nums.length <= 1e5<br> - 0 <= nums[i] <= 1e9<br> - 0 <= p <= nums.length / 2<br>
 * <br>
 * <b>Link:</b><br>
 * <a href="https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/">
 * https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/
 * </a>
 *
 * @param nums input array of integers
 * @param p    number of disjoint pairs to form
 * @return minimum possible maximum difference among all formed pairs
 */
public class Task2616 {
    private static class Node {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];

        while (lo< hi) {
            int mid = (lo + hi) /2;
            if (canFormAtLeastPPairs(nums, p, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private static boolean canFormAtLeastPPairs(int[] nums, int p, int d) {
        int count =0;
        int i = 0;
        while (i +1 < nums.length) {
            if (nums[i+1] - nums[i] <= d) {
                count++;
                i+=2;
            } else {
                i++;
            }
            if(count >=p) {
                return true;
            }
        }
        return count>=p;
    }
}
