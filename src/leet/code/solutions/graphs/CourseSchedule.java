package leet.code.solutions.graphs;

import java.util.*;

/*
https://neetcode.io/problems/course-schedule

You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

The pair [0, 1], indicates that must take course 1 before taking course 0.

There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return true if it is possible to finish all courses, otherwise return false.

Example 1:

Input: numCourses = 2, prerequisites = [[0,1]]

Output: true
Explanation: First take course 1 (no prerequisites) and then take course 0.

Example 2:

Input: numCourses = 2, prerequisites = [[0,1],[1,0]]

Output: false
Explanation: In order to take course 1 you must take course 0, and to take course 0 you must take course 1. So it is impossible.

Constraints:

1 <= numCourses <= 1000
0 <= prerequisites.length <= 1000
All prerequisite pairs are unique.

 */
public class CourseSchedule {

    public static void main(String[] args) {

        int[][] prerequisites = {{0,1}};
        int numCourses = 2;

        boolean canFinish =  canFinish(numCourses, prerequisites);

        System.out.println(canFinish);

        int[][] prerequisites2 = {{0,1},{2,1}};
        int numCourses2 = 3;

        boolean canFinish2 =  canFinish(numCourses2, prerequisites2);

        System.out.println(canFinish2);

        int[][] prerequisites3 = {{0,1},{1,0}};//cycle
        int numCourses3 = 2;

        boolean canFinish3 =  canFinish(numCourses3, prerequisites3);

        System.out.println(canFinish3);
    }

    /*
   Time & Space Complexity
         Time complexity:

         O(V+E)
         Space complexity:

         O(V+E)
         Where  V is the number of courses and E is the number of prerequisites.
  */
    private static boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        for (int[] pair : prerequisites) {

            // Initialize empty lists for all courses first This prevents null pointer exceptions when a course has no prerequisites

            // prerequisite[0] is the course, prerequisite[1] is what it depends on
            adjacencyMap.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
        }

        // Check each course for cycles using DFS
        for(int course = 0; course < numCourses; course++){

            Set<Integer> seen = new HashSet<>();

            if(!dfs(course, seen, adjacencyMap)){
                return false;// Cycle found, impossible to finish all courses
            }
        }

        return true;
    }


    private static boolean dfs(int course, Set<Integer> seen, Map<Integer, List<Integer>> adjacencyMap){
        //BASE
        if(seen.contains(course)){    // If we encounter a course already in our current path, we have a cycle
            return false; // cycle detected
        }

        // OPTIMIZATION: If course has no prerequisites, it can definitely be taken
        if(adjacencyMap.get(course) == null || adjacencyMap.get(course).isEmpty()){
            return true;
        }

        // Add current course to the path we're exploring
        seen.add(course);

        // Explore all prerequisites of the current course
        List<Integer> adjacentCources = adjacencyMap.get(course);
        for(int adjacent : adjacentCources){

            // Recursively check if each prerequisite can be completed
            if(!dfs(adjacent, seen, adjacencyMap)){
                return false;
            }

        }

        // CRITICAL: Remove current course from path (backtrack) This allows the course to be visited again in different paths
        seen.remove(course);//BACKTRACK

        // MEMOIZATION TRICK: Clear prerequisites to mark as "processed" This prevents redundant checks in future calls
        adjacencyMap.put(course, Collections.emptyList());

        // No cycle found in this path
        return true;
    }
}
