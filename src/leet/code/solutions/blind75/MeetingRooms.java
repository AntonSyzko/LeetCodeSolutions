package leet.code.solutions.blind75;

import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.ca/all/252.html

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true

 */
public class MeetingRooms {

    public static void main(String[] args) {
        int[][] meetingTimes = {{7,10},{2,4}};
        boolean canAttendAll = canAttendMeetings(meetingTimes);
        System.out.println(canAttendAll);

        int[][] meetingTimes2 = {{0,30},{5,10},{15,20}};
        boolean canAttendAll2 = canAttendMeetings(meetingTimes2);
        System.out.println(canAttendAll2);
    }


    // time O( N log N ) - for sorting
    //space O(1)
    private static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//SOOORT  by start time

        for (int i = 1; i < intervals.length; i++) {

            int  currentIntervalStart = intervals[i][0];
            int previousIntervalEnd =   intervals[i-1][1];

            if (currentIntervalStart < previousIntervalEnd) {// if current START is BEFORE  prev END

                return false;//overlap detected - exit with false

            }

        }
        return true;
    }

    // time O( N log N ) - for sorting
    //space O(1)
    private static boolean canAttendMeetingsEndTimeSorting(int[][] meetingTimes) {

        Arrays.sort(meetingTimes, Comparator.comparingInt(o -> o[1]));//SOOORT !!! by end time

        for (int i = 1; i < meetingTimes.length; i++) {
            int [] prevMeeting = meetingTimes[i - 1];
            int [] currMeeting = meetingTimes[i];

            if(currMeeting[0] < prevMeeting[1]){
                return false;
            }
        }

        return true;
    }
}
