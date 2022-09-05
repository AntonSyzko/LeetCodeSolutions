//package leet.code.solutions.dynamic_programming;
//
//import java.util.Arrays;
//
//public class MazeUniqueWays {
//
//    public static void main(String[] args) {
//        int[][] graph = new int[4][4];
//        System.out.println(Arrays.deepToString(graph));
//        int uniquePathsOfGraph = uniquePaths(2,2);
//        System.out.println(uniquePathsOfGraph);
//    }
//
//    private static int uniquePaths(int n, int m) {
//        if (n < 1 || m < 1) {
//            return 0;
//        }
//
//        if (n == 1 && m == 1) {
//            return 1;
//        }
//
//        return uniquePaths(n - 1, m) + uniquePaths(n, m - 1);
//    }
//
//    private static int uniquePathsWithBacktracking(int n, int m) {
//
//    }
