package leet.code.solutions.blind75;


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
            int numOfDaysRequired = minMeetingRooms(intervals);
            System.out.println(numOfDaysRequired);

        List<Interval> intervals2 = List.of(new Interval(7,10), new Interval(2,4));
        int  numOfDaysRequired2 = minMeetingRooms(intervals2);
        System.out.println(numOfDaysRequired2);
    }

    private static int minMeetingRooms2(List<Interval> intervals) {
        if(intervals.isEmpty()) return 0;

        if(intervals.size() == 1) return 1;

        int size = intervals.size();

        int[] startTimes = new int[size];
        int[] endTimes = new int[size];

        for (int i = 0; i < size; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int  numberOfDays = 0;

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

       int size = intervals.size();

        int[] startTimes = new int[size];
        int[] endTimes = new int[size];

        for (int i = 0; i < size; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int  numberOfDays = 0;

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
        intervals.sort((a, b) -> a.start - b.start);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();//min heap of interval ENDINGs

        for (Interval interval : intervals) {//mind on first iteration min heap will be empty

            if (!minHeap.isEmpty() // non empty check to avoid NPE
                    &&
                    minHeap.peek() <= interval.start) {//curr min ENDING  is BEFORE curr interval START

                minHeap.poll();//delete as non overlapping

            }

            minHeap.offer(interval.end);//add ENDING to min heap

        }
        return minHeap.size();//number of overlapping intervals left in HEAP - is the number of days needed
    }

    //time O( n log N)
    //space O(n)
    private static int minMeetingRoomsHeap2(List<Interval> intervals) {

        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort((a, b) -> a.start - b.start);//sort by start time

        Queue<Interval> minHipByEndTime = new PriorityQueue<>((a,b) -> a.end - b.end);//min hip stores entire Interval

        for (Interval interval : sorted) {

            if(!minHipByEndTime.isEmpty() && minHipByEndTime.peek().end <= interval.start){
                minHipByEndTime.poll();
            }

            minHipByEndTime.offer(interval);
        }

        return minHipByEndTime.size();
    }



        private static int minIntervalRoomsRequired(Interval[] intervals) {

        if(intervals == null || intervals.length==0) return 0;

        Arrays.sort(intervals, (a,b) -> a.start - b.start);

        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);
        minHeap.offer(intervals[0]);

        for (int i = 1; i < intervals.length ; i++) {

            Interval curr = intervals[i];
            Interval earliest = minHeap.remove();

            if(curr.start >= earliest.end){//no overlap

                earliest.end = curr.end; // expand earliest ending as non overlapping intervals - second ending is further in time

            }else{//overlap

                minHeap.offer(curr);//add to min heap
            }

            minHeap.offer(earliest);//save previously removed

        }

        return minHeap.size();


    }

    private static class Interval {
      public int start, end;
      public Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
  }
}
