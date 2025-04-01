package leet.code.solutions.blind75;

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
        if(intervals.length <= 1) return intervals;

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        // Add first interval to start
        int[] startingInterval = intervals[0];
        result.add(startingInterval);

        for (int i = 1; i < intervals.length; i++) {//start from second interval

            // Get the last interval in our result list
            int[] lastInterval = result.get(result.size() - 1);//getLast()

            // Current interval we're examining
            int[] currentInterval = intervals[i];

            // If there is an overlap - curr START <= last END
            if (currentInterval[0] <= lastInterval[1]) {
                // Merge by updating the END of the last interval if needed
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]); // as this is object - updating it will update it straight in result , no need to add() expolicitly

            } else {
                // No overlap, add the current interval to result
                result.add(currentInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public int[][] mergeOptimal(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = merged.get(merged.size() - 1);

            // If current interval starts before or exactly when last interval ends
            if (current[0] <= last[1]) {
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
