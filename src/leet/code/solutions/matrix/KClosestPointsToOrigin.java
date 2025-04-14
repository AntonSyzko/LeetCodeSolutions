package leet.code.solutions.matrix;

import java.util.*;

/*
https://leetcode.com/problems/k-closest-points-to-origin/description/

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)^2 + (y1 - y2)^2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).



Example 1:


Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.


Constraints:

1 <= k <= points.length <= 104
-10^4 <= xi, yi <= 10^4
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
       int[][] coordinates = {{1,3},{-2,2}};
       int k = 1;

       int[][] res = KClosestPointsToOrigin.kClosest(coordinates, k);

        System.out.println(Arrays.deepToString(res));

        int[][] coordinates2 = {{3,3},{5,-1},{-2,4}};
         k = 2;

        int[][] res2 = KClosestPointsToOrigin.kClosest(coordinates2, k);

        System.out.println(Arrays.deepToString(res2));

        int[][] coordinates3 = {{0,1},{1,0}};
         k = 2;

        int[][] res3 = KClosestPointsToOrigin.kClosest(coordinates3, k);

        System.out.println(Arrays.deepToString(res3));
    }


    private static int[][] kClosest(int[][] points, int k) {

        if(points == null || points.length == 0){
            return null;
        }

        if(k == 0){
            return null;
        }

        //√(x1 - x2)^2 + (y1 - y2)^2)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            int distanceA = (a[0] * a[0]) + (a[1] * a[1]);
            int distanceB = (b[0] * b[0]) + (b[1] * b[1]);
            return distanceB - distanceA; // For max heap (farthest points first)
        });

        for (int row = 0; row < points.length; row++) {

                int[] coord = points[row];

               maxHeap.offer(coord);

               if(maxHeap.size() > k){//limit map size to K

                    maxHeap.remove();//we are dropping max ones , keeping smallest left

                }

        }

        //now in max heap only smallest left

        int[][] res = new int[k][2];//always 2 columns anyways

        while(k-- >0){

            res[k] = maxHeap.remove();
        }

        return res;
    }

    /**
     When calculating distance to the origin (0, 0), this becomes:
     √((x - 0)² + (y - 0)²) = √(x² + y²)

     The formula becomes simply √(x² + y²)
     */

    private static int countDistanceToZero(int x, int y) {
       return  (x * x) + (y * y);
    }
}
