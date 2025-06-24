package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/distance-between-bus-stops/description/

A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.

The bus goes along both directions i.e. clockwise and counterclockwise.

Return the shortest distance between the given start and destination stops.


Example 1:

Input: distance = [1,2,3,4], start = 0, destination = 1
Output: 1
Explanation: Distance between 0 and 1 is 1 or 9, minimum is 1.

Example 2:

Input: distance = [1,2,3,4], start = 0, destination = 2
Output: 3
Explanation: Distance between 0 and 2 is 3 or 7, minimum is 3.
 */
public class DistanceBetweenBusStops {
    public static void main(String[] args) {
        // Test cases
        int[] distance1 = {1, 2, 3, 4};
        System.out.println(distanceBetweenBusStops(distance1, 0, 1)); // Expected: 1
        System.out.println(distanceBetweenBusStops(distance1, 0, 2)); // Expected: 3
        System.out.println(distanceBetweenBusStops(distance1, 0, 3)); // Expected: 4
    }


    public static int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // Step 1: Ensure start <= destination for easier calculation
        if (start > destination) {
            //swap start and dest
            int temp = start;
            start = destination;
            destination = temp;
        }

        // Step 2: Calculate clockwise distance from start to destination
        int clockwise = 0;

        for (int i = start; i < destination; i++) {//start < destination
            clockwise += distance[i];
        }

        // Step 3: Calculate total circle distance
        int total = 0;

        for (int dist : distance) {//traverse all
            total += dist;
        }

        // Step 4: Counterclockwise = total - clockwise
        int counterclockwise = total - clockwise;

        // Step 5: Return minimum distance
        return Math.min(clockwise, counterclockwise);
    }
}

/*
ALGORITHM EXPLANATION:

On a CIRCULAR bus route, there are only 2 paths between any two stops:
1. Clockwise direction
2. Counterclockwise direction

The shortest distance is the minimum of these two.

KEY INSIGHT: counterclockwise = total_distance - clockwise_distance

Time Complexity: O(n) - two passes through array
Space Complexity: O(1) - constant extra space
*/

