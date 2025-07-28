package leet.code.solutions.greedy;

import java.util.Arrays;

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

    /*
        Time Complexity: O(n log n) due to sorting
        Space Complexity: O(1) additional space
     */
    private static int maxActivities(Activity[] activities) {

        int maxActivitiesPossible = 1;//always 1 activity possible

        //sort by END time
        Arrays.sort(activities,(a, b) -> a.finish - b.finish);

        //pick first activity as a start
        Activity prevActivity = activities[0];

        for (int i = 1; i < activities.length ; i++) {//start from 1 ( second activity)

           Activity currentActivity = activities[i];//by index

            if(currentActivity.start > prevActivity.finish){//curr start > prev END

                maxActivitiesPossible++;

                prevActivity = currentActivity;//reset prev  to curr
            }
        }

        return maxActivitiesPossible;
    }

    // Activity class to store start and finish times
    private static class Activity {
        int start;
        int finish;

        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }
}
