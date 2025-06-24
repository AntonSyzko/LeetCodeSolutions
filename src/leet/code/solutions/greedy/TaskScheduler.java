package leet.code.solutions.greedy;

import java.util.*;

/*

https://leetcode.com/problems/task-scheduler/description/

You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task.
Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.

Example 1:

Input: tasks = ['A','A','A','B','B','B'], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.


Example 2:

Input: tasks = ['A','C','A','B','D','B'], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ['A','A','A', 'B','B','B'], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

Constraints:

1 <= tasks.length <= 104
tasks[i] is an uppercase English letter.
0 <= n <= 100
 */
public class TaskScheduler {

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int cooldown = 2;

        int intervalsToComplete = leastInterval(tasks, cooldown);
        System.out.println(intervalsToComplete);

        char[] tasks2 = {'A','C','A','B','D','B'};
        int cooldown2 = 1;

        int intervalsToComplete2 = leastInterval(tasks2, cooldown2);
        System.out.println(intervalsToComplete2);

        char[] tasks3 = {'A','A','A','B','B','B'};
        int cooldown3 = 3;

        int intervalsToComplete3 = leastInterval(tasks3, cooldown3);
        System.out.println(intervalsToComplete3);
    }


    private static int leastInterval(char[] tasks, int cooldown) {
      Map<Character, Integer> taskTimesAppears = new HashMap<>();

      for (char task : tasks) {
          //task: times it appered in [] tasks
          taskTimesAppears.put(task, taskTimesAppears.getOrDefault(task, 0) + 1);
      }

        Queue<Integer> maxHip = new PriorityQueue<>((a,b) -> b - a);
        maxHip.addAll(taskTimesAppears.values());//max hip of ALL task appearances

       int cpuCyclesRes = 0;

       while (!maxHip.isEmpty()) {

           List<Integer> processedTasks = new ArrayList<>();

           for(int pause = 0; pause <= cooldown ; pause++){//pick from max hip with interval of cooldown

                   if(!maxHip.isEmpty()){

                       int currentTaskFromMaxHip = maxHip.remove();
                       //add to processed until cooldown is not exceeded
                      processedTasks.add(currentTaskFromMaxHip);//this will finish 1 task at a time

                   }
           }

           for(int task : processedTasks){//for tasks that aggregated

               task--;//-- means complete 1 task

               if(task  > 0){// but add back to max hip what is left
                   maxHip.add(task);//decremented task added back to Q
               }
           }

           //if hip is empty ADD just processed tasks, otherwise cooldown period + 1
           cpuCyclesRes += maxHip.isEmpty() ? processedTasks.size() :  cooldown + 1;
       }

       return cpuCyclesRes;
    }
}
