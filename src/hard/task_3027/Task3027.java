package hard.task_3027;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * LeetCode — 2976. Find the Number of Ways to Place People II
 * <br><br>
 * You are given an array {@code points} of size {@code n x 2}, where {@code points[i] = [x_i, y_i]} are integer
 * coordinates on a 2D plane.<br> Directions are defined as: right → positive X, left → negative X, up → positive Y,
 * down → negative Y.<br>
 * <br>
 * You must place {@code n} people (включая Alice и Bob) так, чтобы в каждой точке оказался ровно один человек. Далее
 * Alice строит прямоугольный «забор» с верхним левым углом в её позиции и нижним правым — в позиции Bob. Забор может
 * иметь нулевую площадь (то есть быть линией). Если какой-либо другой человек находится внутри прямоугольника или на
 * его границе, Alice становится грустной.<br>
 * <br>
 * <b>Task:</b><br>
 * Return the number of ordered pairs of points (Alice position, Bob position) such that Alice can build the fence (with
 * Alice strictly as the upper-left corner and Bob strictly as the lower-right corner) and no other person lies inside
 * or on the fence. If either corner role is violated, such a pair is invalid.<br>
 * <br>
 * <b>Important:</b><br>
 * Alice's position must be the <i>upper-left</i> corner, and Bob's must be the <i>lower-right</i> corner of the fence.
 * For example, with corners (1,1), (1,3), (3,1), (3,3):
 * <ul>
 *   <li>Alice at (3,3) and Bob at (1,1) — invalid (roles swapped: not upper-left / lower-right).</li>
 *   <li>Alice at (1,3) and Bob at (1,1) — invalid (Bob is not lower-right).</li>
 * </ul>
 * <br>
 * <b>Examples:</b>
 * <pre>
 * Example 1:
 * Input:  points = [[1,1],[2,2],[3,3]]
 * Output: 0
 * Explanation: No way to pick Alice (UL) and Bob (LR) without including someone else in/on the fence.
 *
 * Example 2:
 * Input:  points = [[6,2],[4,4],[2,6]]
 * Output: 2
 * Explanation:
 *   Valid:
 *     - Alice (4,4), Bob (6,2)
 *     - Alice (2,6), Bob (4,4)
 *   Invalid:
 *     - Alice (2,6), Bob (6,2) — point (4,4) lies inside the fence.
 *
 * Example 3:
 * Input:  points = [[3,1],[1,3],[1,1]]
 * Output: 2
 * Explanation:
 *   Valid:
 *     - Alice (1,1), Bob (3,1)
 *     - Alice (1,3), Bob (1,1)
 *   Invalid:
 *     - Alice (1,3), Bob (3,1) — (1,1) lies on the fence.
 * </pre>
 *
 * <b>Constraints:</b><br>
 * - 2 <= n <= 1000<br>
 * - points[i].length == 2<br>
 * - -1e9 <= points[i][0], points[i][1] <= 1e9<br>
 * - All points are distinct.<br>
 * <br>
 * <b>Link:</b><br>
 * <a href="https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/description/">
 *   https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/description/
 * </a>
 *
 * @param points integer coordinates of n distinct points on the 2D plane
 * @return number of ordered pairs (Alice point as upper-left, Bob point as lower-right) with no other point inside/on
 * the fence
 */
public class Task3027 {
    public static void main(String[] args) {
        int[][] points1 = {{3, 1}, {1, 3}, {1, 1}};
        int[][] points2 = {{0, 3}, {2, 4}, {0, 6}};
        int[][] points3 = {{0, 5}, {3, 1}, {4, 3}, {5, 7}, {6, 2}};


//        System.out.println(numberOfPairs(points1) == 2);
//        System.out.println(numberOfPairs(points2) == 2);
        System.out.println(numberOfPairs(points3) == 2);
    }

    public static int numberOfPairs(int[][] points) {
        ArrayList<int[]> nodes = new ArrayList();

        Arrays.stream(points).forEach(e -> nodes.add(e));

        nodes.sort((o1, o2) -> {
                    return o1[0] != o2[0]
                            ? o1[0] - o2[0]
                            : o2[1] - o1[1];
                }
        );

        int result = 0;

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                int[] a = nodes.get(i);
                int[] b = nodes.get(j);

                if ((a[0] == b[0] && a[1] > b[1]) || (a[1] >= b[1])) {
                    if (areValidPoints(nodes, i, j)) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    private static boolean areValidPoints(ArrayList<int[]> nodes, int aliceindex, int bobIndex) {
        int maxY = nodes.get(aliceindex)[1];
        int minY = nodes.get(bobIndex)[1];

        for (int i = aliceindex + 1; i < bobIndex; i++) {
            int currentY = nodes.get(i)[1];
            if (currentY <= maxY && currentY >= minY) {
                return false;
            }
        }
        return true;
    }
}
