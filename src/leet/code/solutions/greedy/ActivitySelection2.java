package leet.code.solutions.greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
The Activity Selection Problem involves selecting the maximum number of activities
that can be performed by a single person or machine, assuming that a person can only work on a single activity at a time.
Each activity has a start time and finish time.
 */
public class ActivitySelection2 {

    public static void main(String[] args) {
        // Example activities (start time, finish time)
        Activity[] activities = {
                new Activity(1, 3),
                new Activity(2, 5),
                new Activity(0, 7),
                new Activity(5, 9),
                new Activity(8, 10),
                new Activity(3, 12)
        };

        int maxActivities = maxActivities(activities);

        System.out.println("Maximum number of activities: " + maxActivities);
    }

    private static int maxActivities(Activity[] activities) {

        int maxActivitiesPossible = 1;//always 1 activity possible

        //sort by END time
        Arrays.sort(activities,(a, b) -> a.finish - b.finish);

        //picj first activity as a start
        int prevActivityFinish = activities[0].finish;

        for (int act = 1; act < activities.length ; act++) {//start from 1 ( second activity)
            int currActivityStart = activities[act].start;

            if(currActivityStart > prevActivityFinish){//curr start > prev END

                maxActivitiesPossible++;

                int currActivityFinish = activities[act].finish;

                prevActivityFinish = currActivityFinish;//reset prev END to curr's END
            }
        }

        return maxActivitiesPossible;
    }

    // Activity class to store start and finish times
    static class Activity {
        int start, finish;

        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }
}
