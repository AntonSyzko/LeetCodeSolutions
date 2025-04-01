package leet.code.solutions.blind75;

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

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        int removedIntervals = eraseOverlapIntervals(intervals);
        System.out.println(removedIntervals);
    }


    private static int eraseOverlapIntervals(int[][] intervals) {
            if(intervals.length == 0) return 0;

        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0],b[0]));//SOOORT !!!

        int prev = 0;
        int eraseCOunt = 0;

        for (int i = 1; i < intervals.length; i++) {//sterat from 1

            if(intervals[prev][1] > intervals[i][0]) {// prev END is BIGGER than curr START

                if(intervals[prev][1] > intervals[i][1]) {//prev END BIGGER than Curr END
                    prev = i; // we pick LONGEST END to prev

                }//othervise prev remains same as curr END  is not longest

                eraseCOunt++;

            }else{
                prev = i;
            }
        }

        return eraseCOunt;
    }


}
