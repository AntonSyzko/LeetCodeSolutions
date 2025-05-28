package leet.code.solutions.topologicl_sort;

import java.util.*;

public class CourseSchedule2 {

    public static void main(String[] args) {
        int[][] prerequisites = {{0,1}};
        int numCourses = 2;

        int[] corses =  findOrderKhan(numCourses, prerequisites);

        System.out.println(Arrays.toString(corses));

        int[][] prerequisites1 = {{1,0}};
        int numCourses1 = 3;

        int[] corses1 =  findOrderKhan(numCourses1, prerequisites1);

        System.out.println(Arrays.toString(corses1));

        int[][] prerequisites2 = {{0,1},{1,2},{2,0}};
        int numCourses2 = 3;

        int[] corses2 =  findOrderKhan(numCourses2, prerequisites2);

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

    private static int[] findOrderKhan(int numCourses, int[][] prerequisites) {
        // Step 1: Build adjacency list and calculate in-degrees
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build graph and count in-degrees
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];

            // prerequisite -> course (prerequisite must come before course)
            adjList.get(prerequisite).add(course);
            inDegree[course]++; // course has one more incoming edge
        }

        // Step 2: Find all courses with no prerequisites (in-degree = 0) and add them to Q
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process courses level by level
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            // Take a course with no remaining prerequisites
            int currentCourse = queue.poll();
            result.add(currentCourse);

            // Remove this course from the graph by updating its neighbors
            for (int nextCourse : adjList.get(currentCourse)) {
                inDegree[nextCourse]--; // One less prerequisite for nextCourse

                // If nextCourse now has no prerequisites, it can be taken
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // Step 4: Check if all courses can be completed
        if (result.size() == numCourses) {
            return result.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[0]; // Cycle detected, impossible to complete all courses
        }
    }
    }

    /*
=== KAHN'S ALGORITHM EXPLAINED ===

    CORE CONCEPT:
    - Always process nodes with NO incoming edges first
    - Remove processed nodes from the graph
    - Repeat until no more nodes can be processed

    WHY IT WORKS:
    - In a valid topological order, prerequisites must come before dependent courses
    - Courses with in-degree 0 have no prerequisites → safe to take first
    - After taking a course, reduce in-degree of dependent courses
    - If we can't process all courses → cycle exists

    === STEP-BY-STEP WALKTHROUGH ===

    Example: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]

    Step 1: Build Graph
    Graph representation:
    0 → [1, 2]    (course 0 is prerequisite for courses 1 and 2)
    1 → [3]       (course 1 is prerequisite for course 3)
    2 → [3]       (course 2 is prerequisite for course 3)
    3 → []        (course 3 has no dependents)

    In-degrees:
    course 0: 0 incoming edges (no prerequisites)
    course 1: 1 incoming edge  (depends on course 0)
    course 2: 1 incoming edge  (depends on course 0)
    course 3: 2 incoming edges (depends on courses 1 and 2)

    Step 2: Initialize Queue
    queue = [0] (only course 0 has in-degree 0)

    Step 3: Process Level by Level

    Iteration 1:
    - Take course 0 from queue
    - result = [0]
    - Update neighbors: in-degree[1]-- → 0, in-degree[2]-- → 0
    - Add courses 1 and 2 to queue
    - queue = [1, 2]

    Iteration 2:
    - Take course 1 from queue
    - result = [0, 1]
    - Update neighbors: in-degree[3]-- → 1
    - queue = [2]

    Iteration 3:
    - Take course 2 from queue
    - result = [0, 1, 2]
    - Update neighbors: in-degree[3]-- → 0
    - Add course 3 to queue
    - queue = [3]

    Iteration 4:
    - Take course 3 from queue
    - result = [0, 1, 2, 3]
    - No neighbors to update
    - queue = []

    Step 4: Validation
    result.size() = 4 = numCourses ✓
    Return [0, 1, 2, 3]

    === CYCLE DETECTION EXAMPLE ===

    Example: numCourses = 3, prerequisites = [[0,1],[1,2],[2,0]]

    Graph: 0 → [1], 1 → [2], 2 → [0] (forms a cycle!)
    In-degrees: [1, 1, 1] (every course has a prerequisite)

    Initial queue: [] (no course has in-degree 0)
    Result: [] (no courses processed)
    result.size() = 0 ≠ 3, so return empty array

    === TIME & SPACE COMPLEXITY ===

    Time Complexity: O(V + E)
    - V = numCourses, E = number of prerequisites
    - Build graph: O(E)
    - Process each course once: O(V)
    - Process each edge once: O(E)

    Space Complexity: O(V + E)
    - Adjacency list: O(V + E)
    - In-degree array: O(V)
    - Queue: O(V) in worst case
    - Result array: O(V)

    === WHY KAHN'S ALGORITHM IS PREFERRED ===

    1. INTUITIVE: Mimics how you'd naturally approach the problem
    2. EASY TO UNDERSTAND: Clear step-by-step process
    3. HANDLES CYCLES: Automatically detects impossible cases
    4. EFFICIENT: Linear time complexity
    5. WIDELY APPLICABLE: Works for any DAG (Directed Acyclic Graph)
*/

