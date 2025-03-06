package leet.code.solutions.greedy;

import java.util.*;
import java.util.stream.Collectors;

/*
https://www.techiedelight.com/activity-selection-problem/

Activity Selection Problem: Given a set of activities, along with the starting and finishing time of each activity,
find the maximum number of activities performed by a single person assuming that a person can only work on a single activity at a time.

For example,

Input: Following set of activities (1, 4), (3, 5), (0, 6), (5, 7), (3, 8), (5, 9), (6, 10), (8, 11), (8, 12), (2, 13), (12, 14)
Output: (1, 4), (5, 7), (8, 11), (12, 14)
 */
public class ActivitySelection {
    public static void main(String[] args) {
        List<Pair> activities = Arrays.asList(new Pair(1, 4),new Pair(1, 4), new Pair(3, 5),
                new Pair(0, 6), new Pair(5, 7), new Pair(3, 8),
                new Pair(5, 9), new Pair(6, 10), new Pair(8, 11),
                new Pair(8, 12), new Pair(2, 13), new Pair(12, 14));

        List<Pair> result = selectActivity2(activities);
        System.out.println(result);
    }

    public static List<Pair> selectActivity(List<Pair> activities) {
        // `k` keeps track of the index of the last selected activity
        int lastSelectedActivity = 0;

        // set to store the selected activities index
        Set<Integer> result = new HashSet<>();

        // select 0 as the first activity
        if (activities.size() > 0) {
            result.add(0);
        }

        // sort the activities according to their finishing time
        Collections.sort(activities, Comparator.comparingInt(Pair::getFinish));

        // start iterating from the second element of the
        // list up to its last element
        for (int i = 1; i < activities.size(); i++) {
            // if the start time of the i'th activity is greater or equal
            // to the finish time of the last selected activity, it
            // can be included in the activities list

            if (activities.get(i).getStart() >= activities.get(lastSelectedActivity).getFinish()) {
                result.add(i);
                lastSelectedActivity = i;            // update `i` as the last selected activity
            }
        }

        return result.stream().map(activities::get).collect(Collectors.toList());
    }

    public static List<Pair> selectActivity2(List<Pair> activities){
        List<Pair> result = new ArrayList<>();

        int lastSelectedActivity = 0;

        Collections.sort(activities, Comparator.comparingInt(Pair::getFinish));

        if(activities.size() >0){
            result.add(activities.get(0));//add first activity -> after sorting it smallest finish time anyways
        }

        for (int i = 1; i < activities.size(); i++) {//start from SECOND activity
            if(activities.get(i).getStart() >= activities.get(lastSelectedActivity).getFinish()){//if start time of curr one is > than finish of LAST selected
                result.add(activities.get(i));
                lastSelectedActivity = i;
            }
        }

        return result;
    }


    static class Pair {
        int start;
        int finish;

        public Pair(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "\r\n\tPair{" +
                    "start=" + start +
                    ", finish=" + finish +
                    '}';
        }

        public int getStart() {
            return start;
        }

        public int getFinish() {
            return finish;
        }
    }


    }
