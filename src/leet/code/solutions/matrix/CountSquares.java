package leet.code.solutions.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://neetcode.io/problems/count-squares?list=neetcode150

You are given a stream of points consisting of x-y coordinates on a 2-D plane. Points can be added and queried as follows:

Add - new points can be added to the stream into a data structure. Duplicate points are allowed and should be treated as separate points.
Query - Given a single query point, count the number of ways to choose three additional points from the data structure such that the three points and the query point form a square. The square must have all sides parallel to the x-axis and y-axis, i.e. no diagonal squares are allowed. Recall that a square must have four equal sides.
Implement the CountSquares class:

CountSquares() Initializes the object.
void add(int[] point) Adds a new point point = [x, y].
int count(int[] point) Counts the number of ways to form valid squares with point point = [x, y] as described above.

Input:
["CountSquares", "add", [[1, 1]], "add", [[2, 2]], "add", [[1, 2]], "count", [[2, 1]], "count", [[3, 3]], "add", [[2, 2]], "count", [[2, 1]]]

Output:
[null, null, null, null, 1, 0, null, 2]

Explanation:
CountSquares countSquares = new CountSquares();
countSquares.add([1, 1]);
countSquares.add([2, 2]);
countSquares.add([1, 2]);

countSquares.count([2, 1]);   // return 1.
countSquares.count([3, 3]);   // return 0.
countSquares.add([2, 2]);     // Duplicate points are allowed.
countSquares.count([2, 1]);   // return 2.
 */
public class CountSquares {

   private final Map<List<Integer>, Integer> pointsCount;
   private final List<List<Integer>> allPoints;

    public CountSquares() {
        pointsCount = new HashMap<>();
        allPoints = new ArrayList<>();
    }

    /*
    Time add - O(1), count O(n)
    Space O(n)
     */
    public void add(int[] point) {
        int pointX = point[0];
        int pointY = point[1];

        List<Integer> currentPoint = List.of(pointX, pointY);

        pointsCount.put(currentPoint, pointsCount.getOrDefault(currentPoint, 0) + 1);
        allPoints.add(currentPoint);
    }

    public int count(int[] point) {
        int howManySquares = 0;

        int incomingPointX = point[0];
        int incomingPointY = point[1];

        for(List<Integer> points : allPoints) {
            int currentX = points.get(0);
            int currentY = points.get(1);

            if(Math.abs(incomingPointX - currentX) != Math.abs(incomingPointY - currentY) // if abs diff between both X or Y coordinates are NOT SAME - the cannot from a square at all
                    ||
                    incomingPointX == currentX || incomingPointY == currentY) {//same coordinates do not count

                continue;
            }

            List<Integer> diagonalPointsX = List.of(incomingPointX, currentY);//digonal X -> Y
            List<Integer> diagonalPointsY = List.of(currentX, incomingPointY);

            howManySquares += pointsCount.getOrDefault(diagonalPointsX, 0)//add diagonal counts multiplied
                                * // multiply as duplicates boost the count times counts exist ( e.g. 2 - 2, 3 * 3 )
                              pointsCount.getOrDefault(diagonalPointsY, 0);
        }

        return howManySquares;
    }

    public static void main(String[] args) {
        CountSquares countSquares = new CountSquares();
        countSquares.add(List.of(1,1).stream().mapToInt(i -> i).toArray());
        countSquares.add(List.of(2,2).stream().mapToInt(i -> i).toArray());
        countSquares.add(List.of(1,2).stream().mapToInt(i -> i).toArray());

        System.out.println( countSquares.count(List.of(2,1).stream().mapToInt(i -> i).toArray()));
        System.out.println(  countSquares.count(List.of(3,3).stream().mapToInt(i -> i).toArray()));

        countSquares.add(List.of(2,2).stream().mapToInt(i -> i).toArray());
        System.out.println(  countSquares.count(List.of(2,1).stream().mapToInt(i -> i).toArray()));
    }
}