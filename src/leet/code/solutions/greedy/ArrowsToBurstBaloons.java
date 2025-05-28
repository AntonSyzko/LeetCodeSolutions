package leet.code.solutions.greedy;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

Constraints:

1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1
 */
public class ArrowsToBurstBaloons {

    public static void main(String[] args) {

        int[][] points1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println("Example 1: " + findMinArrowShots(points1));

        int[][] points2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println("Example 2: " + findMinArrowShots(points2));

        int[][] points3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println("Example 3: " + findMinArrowShots(points3));
    }

    /*
    Time Complexity:
              O(n log n), dominated by the sorting operation

    Space Complexity:
            O(1) or O(log n), depending on the sorting implementation
     */
    private static int findMinArrowShots(int[][] points) {
        int numberOfArrowsNeeded = 1;

        // Sort by end position (xend)
        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));

        int arrowStartingPoint = points[0][1];//at the end of first balloon position

        for (int i = 1; i < points.length; i++) {

            int currentBalloonStartPosition = points[i][0];

            // If the current balloon starts after the arrow position, we need a new arrow
            if(currentBalloonStartPosition > arrowStartingPoint){
                numberOfArrowsNeeded++;

                int currentBalloonEndPosition = points[i][1];
                arrowStartingPoint = currentBalloonEndPosition;//point arrow at current balloon's end pos
            }
            // Otherwise, the current balloon can be burst by the existing arrow
        }

        return numberOfArrowsNeeded;
    }
}
