package leet.code.solutions.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertIntervals2 {

    public static void main(String[] args) {
        List<Interval> intervals = List.of(new Interval(1,3), new Interval(6,9));
        System.out.println(intervals);
        List<Interval> merged =  insert(intervals,  new Interval(2,5));
        System.out.println(merged);
    }

    private static List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new ArrayList<>();

        for (Interval curr : intervals) {//intervals are sorted by design

            if(curr.end < newInterval.start){//curr even BEFORE new start

                result.add(curr);

            }else if (curr.start > newInterval.end){ // curr start AFTER new end

                result.add(newInterval);//insert, anyways we are sorted list and means no overlap seen

                newInterval = curr;//role switch

            }else {//overlap
                //merge
                newInterval = new Interval(Math.min(curr.start, newInterval.start), Math.max(curr.end, newInterval.end));
            }
        }
        // POST LOOP !!!!
        // Add final interval
        result.add(newInterval);

        return result;
    }

    private static   class Interval {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
