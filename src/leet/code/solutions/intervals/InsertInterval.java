package leet.code.solutions.intervals;

/*
https://leetcode.com/problems/insert-interval/

You are given an array of non-overlapping intervals intervals where intervals[i] = [start-i, end-i] represent the start and the end of the i-th interval and intervals is sorted in ascending order by start-i.
 You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by start-i and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]


Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval {

    public static void main(String[] args) {
      int[][] intervals = {
              {1,3},
              {6,9}
      };
       int [] newInterval = {2,5};

       int[][] inserted = insert(intervals,newInterval);
        System.out.println(Arrays.deepToString(inserted));
    }

    // O(N)
    private static int[][] insert(int[][] intervals, int[] newInterval) {
        final int len = intervals.length;

        List<int[]> ans = new ArrayList<>();//list of arrays

        int movingLeftIndex = 0;

        //populate RES before we face the overlapping conflict of START and END
        while (movingLeftIndex < len // boundaries
                && // AND
                intervals[movingLeftIndex][1] < newInterval[0]){//existing interval END is LESS than new START

            ans.add(intervals[movingLeftIndex]);//add existing to RES
            movingLeftIndex++; //move on index in existing

        }

        // reached the spot of merge
        // Merge overlapping intervals.
        while (movingLeftIndex < len // boundaries
                &&
                intervals[movingLeftIndex][0] <= newInterval[1]) {//while existing START  LESS than new END

            //here we altering the very input ->  re-creating NEW INTERVAL
            newInterval[0] = Math.min(newInterval[0], intervals[movingLeftIndex][0]);//new START is MIN of it's value OR current start
            newInterval[1] = Math.max(newInterval[1], intervals[movingLeftIndex][1]);//new END is MAX of it's val OR current END

            movingLeftIndex++;
        }

        //after overlap constructed and  start-end merged -> add to res
        ans.add(newInterval);

        //as we have only ONE interval to merge - and we did it, add left overs to res
        while (movingLeftIndex < len){//what is remaining

            ans.add(intervals[movingLeftIndex]);
            movingLeftIndex++;

        }

        return ans.toArray(int[][] :: new);
    }




    private static int[][] insert2(int[][] intervals, int[] newInterval) {

        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        int left  = 0;
        int right = intervals.length ;

        List<int[]> out = new LinkedList<>();//list of arrays

        while (left < right && newStart > intervals[left][0]) {

             out.add(intervals[left]);
             left++;

        }

        int [] intervalToInsert = new int[2];

        if(out.isEmpty() ||  out.getLast()[1] < newStart){//existing END is LESS than new START
            out.add(newInterval);

        }else{//exists overlap

            intervalToInsert = out.removeLast();//poll from RES to override

            intervalToInsert[1] = Math.max(intervalToInsert[1],newEnd);// new END is MAX of input new END or one we have stored in RES so far

            out.add(intervalToInsert);// re - insert back to RES

        }

        //after we have merged - process left overs
        while (left < right) {

            intervalToInsert = intervals[left];
            left++;

            int start = intervalToInsert[0];
            int end = intervalToInsert[1];

            if(out.getLast()[1] < start){

                out.add(intervalToInsert);

            }else{//exists overlap

                intervalToInsert = out.removeLast();
                intervalToInsert[1] = Math.min(intervalToInsert[1],end);
                out.add(intervalToInsert);

            }
        }

        return out.toArray(new int[out.size()][]);
    }
}
