package leet.code.solutions.priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://neetcode.io/problems/k-closest-points-to-origin

You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates of a point on an X-Y axis plane. You are also given an integer k.

Return the k closest points to the origin (0, 0).

The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).

You may return the answer in any order.

Example 1:

Input: points = [[0,2],[2,2]], k = 1

Output: [[0,2]]
Explanation : The distance between (0, 2) and the origin (0, 0) is 2. The distance between (2, 2) and the origin is sqrt(2^2 + 2^2) = 2.82842. So the closest point to the origin is (0, 2).

Example 2:

Input: points = [[0,2],[2,0],[2,2]], k = 2

Output: [[0,2],[2,0]]
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][] coord = {{0,2},{2,0},{2,2}};
        int k = 2;
        int[][] closest = kClosest(coord, k);
        System.out.println(Arrays.deepToString(closest));

        int[][] coord2 = {{0,2},{2,2}};
        int k2 = 1;
        int[][] closest2 = kClosest(coord2, k2);
        System.out.println(Arrays.deepToString(closest2));
    }


    /*
    Time and Space Complexity:

        Time Complexity: O(n log n) where n is the number of points

        Building the heap takes O(n log n) time
        Extracting k elements takes O(k log n) time
        Overall: O(n log n + k log n) = O(n log n) since k ≤ n

        Space Complexity: O(n) for storing all points in the heap
     */
    private static int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][2];

        //(sqrt((x1 - x2)^2 + (y1 - y2)^2)).
        // Create a min heap based on distance to origin
        // For efficiency, we can use distance squared (no need for square root)
        Queue<int[]> minHip = new PriorityQueue<>((a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]));
        for(int[] coordinates : points) {
            minHip.add(coordinates);
        }

        // Extract the k closest points
        for(int i = 0; i < k; i++) {
            res[i] = minHip.poll();
        }
        return res;
    }
}
