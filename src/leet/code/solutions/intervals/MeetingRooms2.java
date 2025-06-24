package leet.code.solutions.intervals;

import java.util.*;

/*
https://neetcode.io/problems/meeting-schedule-ii

Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of days required to schedule all meetings without any conflicts.

Example 1:

Input: intervals = [(0,40),(5,10),(15,20)]

Output: 2

Explanation:
day1: (0,40)
day2: (5,10),(15,20)

Example 2:

Input: intervals = [(4,9)]

Output: 1
Note:

(0,8),(8,10) is not considered a conflict at 8
Constraints:

0 <= intervals.length <= 500
0 <= intervals[i].start < intervals[i].end <= 1,000,000
*/
public class MeetingRooms2 {


    public static void main(String[] args) {
            List<Interval> intervals = List.of(new Interval(0, 40), new Interval(5, 10), new Interval(15, 20));
            int numOfDaysRequired = minMeetingRoomsHeap(intervals);
            System.out.println(numOfDaysRequired);

            List<Interval> intervals2 = List.of(new Interval(7,10), new Interval(2,4));
            int  numOfDaysRequired2 = minMeetingRoomsHeap(intervals2);
            System.out.println(numOfDaysRequired2);
    }

    private static int minMeetingRooms2(List<Interval> intervals) {
        if(intervals.isEmpty()) return 0;

        if(intervals.size() == 1) return 1;

        int  numberOfDays = 0;

        int size = intervals.size();

        int[] startTimes = new int[size];
        int[] endTimes = new int[size];

        for (int i = 0; i < size; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startTimeIndex = 0;
        int endTimeIndex = 0;

        while (startTimeIndex < startTimes.length){

            if( startTimes[startTimeIndex] >= endTimes[endTimeIndex]){//non overlapping meetings
                numberOfDays--;
                endTimeIndex++;
            }
            //some overlap occurrs
            startTimeIndex++;//move start
            numberOfDays++;// add number of rooms
        }

        return numberOfDays;
    }

    private static int minMeetingRooms(List<Interval> intervals) {
        if(intervals.isEmpty()) return 0;

        if(intervals.size() == 1) return 1;


       int  numberOfDays = 0;

       int size = intervals.size();

        int[] startTimes = new int[size];
        int[] endTimes = new int[size];

        for (int i = 0; i < size; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startTimeIndex = 0;
        int endTimeIndex = 0;

        int ongoingMeetingRoomsCount = 0;

        while (startTimeIndex < startTimes.length) {

            if (startTimes[startTimeIndex] < endTimes[endTimeIndex]) {//overlap

                startTimeIndex++;
                ongoingMeetingRoomsCount++;

            }else{//non overlap

                endTimeIndex++;
                ongoingMeetingRoomsCount--;
            }

            numberOfDays = Math.max(numberOfDays, ongoingMeetingRoomsCount);

        }

        return numberOfDays;
    }

    //time O( n log N)
    //space O(n)
    private static int minMeetingRoomsHeap(List<Interval> intervals) {

        if(intervals == null || intervals.isEmpty()) return 0;

        // Create a mutable copy
        List<Interval> sortedIntervals = new ArrayList<>(intervals);
        Collections.sort(sortedIntervals, (a,b) -> a.start - b.start);

        PriorityQueue<Integer> minHeapOfEnds = new PriorityQueue<>();//stores MIN END of intervals

        for (Interval curr : sortedIntervals) {//mind on first iteration min heap will be empty

            if (!minHeapOfEnds.isEmpty() // non empty check to avoid NPE
                    &&
                    minHeapOfEnds.peek() <= curr.start) {//most min time is BEFORE curr  start

                minHeapOfEnds.poll();//delete as NON  overlapping, min most is not the most min

            }
            //as no overlap occurred, most min will be current END
            minHeapOfEnds.offer(curr.end);

        }

        return minHeapOfEnds.size();//number of overlapping intervals left in HEAP - is the number of days needed
    }


    private static class Interval {
      public int start;
      public int     end;

      public Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }

  }
}
