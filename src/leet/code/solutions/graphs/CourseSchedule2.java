package leet.code.solutions.graphs;

import java.util.*;

/*

https://neetcode.io/problems/course-schedule-ii

You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of them. If it's not possible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 3, prerequisites = [[1,0]]

Output: [0,1,2]
Explanation: We must ensure that course 0 is taken before course 1.

Example 2:

Input: numCourses = 3, prerequisites = [[0,1],[1,2],[2,0]]

Output: []
Explanation: It's impossible to finish all courses.

Constraints:

1 <= numCourses <= 1000
0 <= prerequisites.length <= 1000
All prerequisite pairs are unique.
 */
public class CourseSchedule2 {

    public static void main(String[] args) {
        int[][] prereq = {{1,0}};
      int[] courses = findOrder(3, prereq);
      System.out.println(Arrays.toString(courses));

        int[][] prereq2 = {{0,1},{1,2}, {2,0}};
        int[] courses2 = findOrder(2, prereq2);
        System.out.println(Arrays.toString(courses2));

        int[][] prereq3 = {{0, 1}};
        int[] courses3 = findOrder(2, prereq3);
        System.out.println(Arrays.toString(courses3));
    }

    private static int[] findOrder(int numCourses, int[][] prerequisites) {
       Map<Integer, List<Integer>> coursePrerequisites = new HashMap<>();
        Set<Integer> seenCourses = new HashSet<>();
        Set<Integer> cycleCourses = new HashSet<>();

        for (int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            coursePrerequisites.computeIfAbsent(course, k -> new ArrayList<>()).add(pre);
        }

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < numCourses;i++){
            if(!validCOurse(i, seenCourses,  cycleCourses, coursePrerequisites, result)){
                return new int[0];
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /*
    Time & Space Complexity
        Time complexity:

                O(V+E)
                Space complexity:

                 O(V+E)
        Where V is the number of courses and  E is the number of prerequisites.
     */
    private static boolean validCOurse(int i, Set<Integer> seenCourses, Set<Integer> cycleCourses,  Map<Integer, List<Integer>> coursePrerequisites, List<Integer> result) {

        if(cycleCourses.contains(i)) return false;

        if(seenCourses.contains(i)) return true;

        cycleCourses.add(i);

        for(Integer prerequisite : coursePrerequisites.getOrDefault(i,Collections.emptyList())){

            if(!validCOurse(prerequisite, seenCourses, cycleCourses, coursePrerequisites, result)){
                return false;
            }
        }

        cycleCourses.remove(i);//backtrack

        seenCourses.add(i);//mark as visited

        //recursion DFS will make sure that ALL the courses this current was depend on are processed BEFORE and already in RES prior to curr
        result.add(i);

        return true;
    }
}
