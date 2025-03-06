package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Problem:
Given a collection of intervals, merge all overlapping intervals.
For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].


 */
public class MergeIntervals {

    public static void main(String[] args) {
      ArrayList<Interval> intervals = new ArrayList<Interval>();
      intervals.add(new Interval(1, 3));
      intervals.add(new Interval(2, 6));
      intervals.add(new Interval(8, 10));
      intervals.add(new Interval(15, 18));

      ArrayList<Interval> mergedIntervals = merge(intervals);
      System.out.println(mergedIntervals);
    }

    private static ArrayList<Interval> merge(ArrayList<Interval> intervals) {

            if(intervals==null || intervals.size() <=1){
                return intervals;
            }


            Collections.sort(intervals,(a,b)->a.start - b.start);

            ArrayList<Interval> res = new ArrayList<>();

            Interval previous = intervals.get(0);

            for (int i = 1; i < intervals.size() ; i++) {
                Interval current = intervals.get(i);
                if(previous.end >= current.start){
                    Interval mergedReplacementOfOverlappedOnes = new Interval(previous.start,  current.end);
                    res.add(mergedReplacementOfOverlappedOnes);
                    previous = mergedReplacementOfOverlappedOnes;
                }else{
                    res.add(current);
                    previous = current;
                }
            }
        return res;
        }


    private static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals) {

        if(intervals==null || intervals.size() <=1){
            return intervals;
        }


        Collections.sort(intervals,(a,b)->a.start - b.start);

        ArrayList<Interval> res = new ArrayList<>();

        Interval previous = intervals.get(0);

        for (int i = 1; i < intervals.size() ; i++) {

            Interval current = intervals.get(i);

            if(previous.end >= current.start){

                Interval mergedReplacementOfOverlappedOnes = new Interval(previous.start, Math.max(previous.end, current.start));

                previous = mergedReplacementOfOverlappedOnes;

            }else{

                res.add(previous);

                previous = current;
            }

            res.add(previous);

        }
        return res;
    }


    static class Interval {
        int start;
        int end;

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
