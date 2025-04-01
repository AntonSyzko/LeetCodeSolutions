package leet.code.solutions.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/merge-intervals/description/

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
public class MergeIntervals {

    public static void main(String[] args) {

         int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
         int[][] merged = merge(intervals);
        System.out.println(Arrays.deepToString(merged));

        int[][] intervals2 = {{1, 4},{5,6}};
        int[][] merged2 = merge(intervals2);
        System.out.println(Arrays.deepToString(merged2));

    }

    private static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//SOOORT !!!

        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(intervals[0]);//add first to res

        for (int i = 1; i < intervals.length; i++) {//start from 1

            int[] currentInterval = intervals[i];
            int[] lastMergedInterval = mergedIntervals.get(mergedIntervals.size() - 1);//getLast()

            // Check for overlap (current START <= last END)
            if (currentInterval[0] <= lastMergedInterval[1]) {

                // Merge intervals by extending the end if needed
                lastMergedInterval[1] = Math.max(lastMergedInterval[1], currentInterval[1]);// pick LONGEST END

            } else {// No overlap, add as a new interval

                mergedIntervals.add(currentInterval);//just add current
            }
        }

        return mergedIntervals.toArray(new int[0][]);
    }

    public int[][] mergeOptimal(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));// SOOOORT !!!

        List<int[]> merged = new ArrayList<>();//RES
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {

            int[] current = intervals[i];
            int[] last = merged.get(merged.size() - 1);//getLast();

            // If current interval starts before or exactly when last interval ends
            if (current[0] <= last[1]) {//curr START is LESS than lastly processed END
                // Extend the end of last interval if needed
                last[1] = Math.max(last[1], current[1]);

            } else {
                // Add new non-overlapping interval
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
