package leet.code.solutions.topologicl_sort;

import java.util.*;

public class CourseScheduleTopSortWithDFS {

    public static void main(String[] args) {
        int[][] prerequisites = {{0,1}};
        int numCourses = 2;

        int[] corses =  findOrderDFS(numCourses, prerequisites);

        System.out.println(Arrays.toString(corses));

        int[][] prerequisites1 = {{1,0}};
        int numCourses1 = 3;

        int[] corses1 =  findOrderDFS(numCourses1, prerequisites1);

        System.out.println(Arrays.toString(corses1));

        int[][] prerequisites2 = {{0,1},{1,2},{2,0}};
        int numCourses2 = 3;

        int[] corses2 =  findOrderDFS(numCourses2, prerequisites2);

        System.out.println(Arrays.toString(corses2));
    }

        /*
    Time & Space Complexity
        Time complexity:
            O(V+E)
        Space complexity:
            O(V+E)
        Where :V is the number of courses and E is the number of prerequisites.
     */

    private static int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        int[] inDegrees = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisiste = pre[1];
            adjacencyList.get(prerequisiste).add(course);
            inDegrees[course]++;
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                 dfs(i, inDegrees, adjacencyList, res);
            }
        }

        if(res.size() != numCourses){
            return new int[0];
        }else{
            return res.stream().mapToInt(i->i).toArray();
        }
    }

    private static void dfs(int course, int[] inDegrees, List<List<Integer>> adjacencyList, List<Integer> res) {
         res.add(course);
         inDegrees[course]--;

         List<Integer> adjacents = adjacencyList.get(course);
         for(int nextCOurse : adjacents) {
             inDegrees[nextCOurse]--;
             if(inDegrees[nextCOurse] == 0) {
                 dfs(nextCOurse, inDegrees, adjacencyList, res);
             }
         }
    }
    }
