package medium.task_1792;

import java.util.PriorityQueue;

/**
 * LeetCode — 1792. Maximum Average Pass Ratio
 * <br><br>
 * You are given a list of classes, where classes[i] = [pass_i, total_i]. In the i-th class there are total_i students
 * and exactly pass_i of them will pass the final exam.<br>
 * You are also given an integer extraStudents — the number of additional brilliant students who are guaranteed
 * to pass any class they join. You need to assign each of these extraStudents to some class (one by one)
 * to maximize the average pass ratio across all classes.<br>
 * <br>
 * <b>Definitions:</b><br>
 * - Pass ratio of a class: pass_i / total_i.<br>
 * - Average pass ratio: (sum of all class pass ratios) / (number of classes).<br>
 * <br>
 * <b>Task:</b><br>
 * Return the maximum possible average pass ratio after assigning the extraStudents optimally.
 * Answers within 1e-5 of the actual value are accepted.
 * <br><br>
 * <b>Examples:</b>
 * <pre>
 * Example 1:
 * Input:  classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * Output: 0.78333
 * Explanation:
 * Assign both extra students to the first class:
 * average = (3/4 + 3/5 + 2/2) / 3 = 0.78333
 *
 * Example 2:
 * Input:  classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * Output: 0.53485
 * </pre>
 *
 * <b>Constraints:</b><br>
 * - 1 <= classes.length <= 1e5<br>
 * - classes[i].length == 2<br>
 * - 1 <= pass_i <= total_i <= 1e5<br>
 * - 1 <= extraStudents <= 1e5<br>
 * <br>
 * <b>Link:</b><br>
 * <a href="https://leetcode.com/problems/maximum-average-pass-ratio/description/">
 *   https://leetcode.com/problems/maximum-average-pass-ratio/description/
 * </a>
 */
public class Task1792 {
    public static void main(String[] args) {
        // Тесткейсы: { {classes}, extraStudents, expected }
        Object[][] tests = {
                { new int[][]{{1,2},{3,5},{2,2}}, 2, 0.78333 },
                { new int[][]{{2,4},{3,9},{4,5},{2,10}}, 4, 0.53485 }
        };

        for (int i = 0; i < tests.length; i++) {
            int[][] classes = (int[][]) tests[i][0];
            int extra = (int) tests[i][1];
            double expected = (double) tests[i][2];

            double result = maxAverageRatio(classes, extra);

            if (Math.abs(result - expected) < 1e-5) {
                System.out.println("[OK] Test " + (i+1) +
                        " → got " + result + ", expected " + expected);
            } else {
                System.out.println("[FAIL] Test " + (i+1) +
                        " → got " + result + ", expected " + expected);
            }
        }
    }

    private static class Node{
        int p;
        int t;

        public Node(int p, int t) {
            this.p = p;
            this.t = t;
        }
    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(calculateRatioIncreasing(b.p, b.t), calculateRatioIncreasing(a.p, a.t))
        );

        for (int i = 0; i < classes.length; i++) {
            queue.add(new Node(classes[i][0], classes[i][1]));
        }

        for (int i = 0; i < extraStudents; i++) {
            Node node = queue.poll();
            node.p++;
            node.t++;

            queue.offer(node);
        }

        double sum = 0;
        for (Node node : queue) {
            sum += ((double) node.p) / node.t;
        }

        return sum / classes.length;
    }

    private static double calculateRatioIncreasing(int p, int t) {
        return  (((double) (p + 1)) / (t + 1)) - ((double) p)/ t;
    }
}
