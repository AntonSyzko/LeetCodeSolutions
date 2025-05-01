package leet.code.solutions.blind75;

import java.util.*;

/*
https://leetcode.com/problems/course-schedule/description/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.


For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    public static void main(String[] args) {
         int[][] prerequsuites = new int [][] {{1,0},{0,3}};
         int numOfCOurses = 3;

         boolean canFinish = canFinish(numOfCOurses, prerequsuites);
         System.out.println(canFinish);
    }


    private  static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseToPrerequsuites = new HashMap<>();

        for (int[] prerequsuite : prerequisites) {

            int coursePrerequiste = prerequsuite[0];
            int course = prerequsuite[1];

            if(courseToPrerequsuites.containsKey(course)){

                courseToPrerequsuites.get(course).add(coursePrerequiste);

            }else {

                List<Integer> nextCourses = new LinkedList<>();
                nextCourses.add(coursePrerequiste);
                courseToPrerequsuites.put(course, nextCourses);

            }
        }

        Set<Integer> visitedCourses = new HashSet<>();

        for (int currentCourse = 0; currentCourse < numCourses; currentCourse++) {

            if(!courseSchedule(currentCourse, visitedCourses, courseToPrerequsuites)){
                return false;
            }
        }
        
        return true;
    }

    private static boolean courseSchedule(int currentCourse, Set<Integer> visitedCourses, Map<Integer, List<Integer>> courseToPrerequsuites) {


        //BASE
        if(visitedCourses.contains(currentCourse)){//seen before - we have a cycle
            return false;//fast return
        }
//BASE
        if(courseToPrerequsuites.get(currentCourse) == null){//possible to attend currnt course as null means we can attend
            return true;
        }

        visitedCourses.add(currentCourse);//mark as visited

       for (int prerequisite : courseToPrerequsuites.get(currentCourse)) {

           if(!courseSchedule(prerequisite, visitedCourses, courseToPrerequsuites)){//recurr for every current course's prerequsiste
               return false;//no way
           }

       }

       visitedCourses.remove(currentCourse);//BACKTRACK

        courseToPrerequsuites.put(currentCourse, null);//if we reached here - means we can visist - set as NULL

        return true;//reched here - true
    }
}
