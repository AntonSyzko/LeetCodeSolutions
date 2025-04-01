package leet.code.solutions.intervals;

import java.util.Arrays;

/*
https://leetcode.com/problems/non-overlapping-intervals/description/

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
public class NonOverlappingIntervals {

    /*
    The key insight in your solution is the comparison intervals[prev][1] > intervals[currIndex][1] when there's an overlap.
     This means we're choosing to keep the interval that ends earlier, which is the optimal greedy choice.
     */
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        int removedIntervals = eraseOverlapIntervals(intervals);
        System.out.println(removedIntervals);
    }

    //Time Complexity: O(n log n) for sorting, where n is the number of intervals
    //Space Complexity: O(1) if we don't count the input space
    private static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int eraseCount = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {//start from 1

            // Overlap exists - remove one interval
            if (intervals[i][0] < prevEnd) {//curr START is AFTER than prev END -> overlap detected

                eraseCount++;
                // Keep the interval with earlier end time
                prevEnd = Math.min(prevEnd, intervals[i][1]);//current END is (intervals[i][1])

            } else {  // No overlap - update end time

                prevEnd = intervals[i][1];
            }
        }

        return eraseCount;
    }

    /*
    Alternative Approach: Sort by End Time
An even cleaner approach is to sort by end times directly:
     */
    private static int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        // Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));//SOORT !!! by end time

        int eraseCount = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {//start from 1

            if (intervals[i][0] < prevEnd) {//curr START is AFTER than prev END -> overlap detected

                // Overlap - must remove this interval
                eraseCount++;

            } else {// No overlap - can keep this interval

                prevEnd = intervals[i][1];
            }
        }

        return eraseCount;
    }


    private static int eraseOverlapIntervals3(int[][] intervals) {
            if(intervals.length == 0) return 0;

        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0],b[0]));//SOOORT !!!

        int prev = 0;
        int eraseCOunt = 0;//how many intervals we have erased

        for (int currIndex = 1; currIndex < intervals.length; currIndex++) {//sterat from 1

           // When there's an overlap, keep the interval with the earlier end time
            if(intervals[prev][1] > intervals[currIndex][0]) {// prev END is BIGGER than curr START

                if(intervals[prev][1] > intervals[currIndex][1]) {//prev END BIGGER than Curr END

                    prev = currIndex; // we pick LONGEST END to prev

                }//othervise prev remains same as curr END  is not longest

                eraseCOunt++;//If it overlaps with the previous interval, increment the removal count


            }else{//If no overlap, update the previous interval to the current one
                prev = currIndex;
            }
        }

        return eraseCOunt;
    }


}
