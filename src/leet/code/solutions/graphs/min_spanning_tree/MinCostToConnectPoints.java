package leet.code.solutions.graphs.min_spanning_tree;

import java.util.*;

/*
https://neetcode.io/problems/min-cost-to-connect-points

You are given a 2-D integer array points, where points[i] = [xi, yi]. Each points[i] represents a distinct point on a 2-D plane.

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between the two points, i.e. |xi - xj| + |yi - yj|.

Return the minimum cost to connect all points together, such that there exists exactly one path between each pair of points.

Example 1:

Input: points = [[0,0],[2,2],[3,3],[2,4],[4,2]]

Output: 10
Constraints:

1 <= points.length <= 1000
-1000 <= xi, yi <= 1000
 */
public class MinCostToConnectPoints {

    public static void main(String[] args) {
        int[][] coordinates = {{0,0},{2,2},{3,3},{2,4},{4,2}};
        int minCost = minCostConnectPointsPrims(coordinates);
        System.out.println(minCost);
    }

    /*
    Time & Space Complexity
        Time complexity:O(n^2  log n)

        Space complexity: O(n ^ 2 )

     */
    private static int minCostConnectPointsPrims(int[][] points) {
        int N = points.length;
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int i = 0; i < N; i++) {

            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i + 1; j < N; j++) {

                int x2 = points[j][0];
                int y2 = points[j][1];

                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);

                adj.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{dist, j});
                adj.computeIfAbsent(j, k -> new ArrayList<>()).add(new int[]{dist, i});
            }
        }

        int res = 0;

        Set<Integer> visit = new HashSet<>();

        PriorityQueue<int[]> minH = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minH.offer(new int[]{0, 0});

        while (visit.size() < N) {

            int[] curr = minH.poll();
            int cost = curr[0];
            int index = curr[1];

            if (visit.contains(index)) {
                continue;
            }

            res += cost;

            visit.add(index);

            for (int[] nei : adj.getOrDefault(index, Collections.emptyList())) {

                int neiCost = nei[0];
                int neiIndex = nei[1];

                if (!visit.contains(neiIndex)) {
                    minH.offer(new int[]{neiCost, neiIndex});
                }
            }
        }
        return res;
    }
}
