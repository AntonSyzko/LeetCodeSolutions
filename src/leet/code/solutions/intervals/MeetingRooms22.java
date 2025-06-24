package leet.code.solutions.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
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
public class MeetingRooms22 {

    public static void main(String[] args) {
      Interval interval1 = new Interval(0,30);
      Interval interval2 = new Interval(5,10);
      Interval interval3 = new Interval(15,20);

      Interval[] meetings = new Interval[]{interval1,interval2,interval3};

      int roomsRequired = minMeetingRooms2(meetings);

        System.out.println(roomsRequired);
    }

    private static int minMeetingRooms2(Interval[] intervals) {
          if(intervals == null || intervals.length==0) return 0;

        Arrays.sort(intervals, (a,b) -> a.start - b.start);//sort by START
        Queue<Interval> minHip = new PriorityQueue<>((a,b)-> a.end - b.end);//min hip by END
        minHip.add(intervals[0]);//only first sorted by START added to min hip

        for(int i = 1; i < intervals.length; i++){//from 1

            Interval curr = intervals[i];
            Interval earliestEnd = minHip.remove();

            if(curr.start >= earliestEnd.end){
                earliestEnd.end = curr.end; //extend as it is one big meeting
            }else{
                minHip.add(curr);
            }

            minHip.add(earliestEnd);//restore after extension
        }

        return minHip.size();
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
