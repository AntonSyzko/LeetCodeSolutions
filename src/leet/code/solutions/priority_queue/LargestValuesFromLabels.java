package leet.code.solutions.priority_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
   https://leetcode.com/problems/largest-values-from-labels/description/

    You are given n item's value and label as two integer arrays values and labels. You are also given two integers numWanted and useLimit.

    Your task is to find a subset of items with the maximum sum of their values such that:

    The number of items is at most numWanted.
    The number of items with the same label is at most useLimit.
    Return the maximum sum.

    Example 1:

    Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1

    Output: 9

    Explanation:

    The subset chosen is the first, third, and fifth items with the sum of values 5 + 3 + 1.

    Example 2:

    Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2

    Output: 12

    Explanation:

    The subset chosen is the first, second, and third items with the sum of values 5 + 4 + 3.

    Example 3:

    Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1

    Output: 16

    Explanation:

    The subset chosen is the first and fourth items with the sum of values 9 + 7.

    Constraints:

    n == values.length == labels.length
    1 <= n <= 2 * 104
    0 <= values[i], labels[i] <= 2 * 104
    1 <= numWanted, useLimit <= n
 */

public class LargestValuesFromLabels {

    public static void main(String[] args) {

        int[] values = {5,4,3,2,1};
        int[] labels = {1,1,2,2,3};
        int numsVanted = 3;
        int duplicateLabelsAllowed = 1;

        int maxSum = largestValsFromLabels(values, labels, numsVanted, duplicateLabelsAllowed);
        System.out.println(maxSum);

        int[] values2 = {5,4,3,2,1};
        int[] labels2 = {1,3,3,3,2};
        int numsVanted2 = 3;
        int duplicateLabelsAllowed2 = 2;

        int maxSum2 = largestValsFromLabels(values2, labels2, numsVanted2, duplicateLabelsAllowed2);
        System.out.println(maxSum2);

        int[] values3 = {9,8,8,7,6};
        int[] labels3 = {0,0,0,1,1};
        int numsVanted3 = 3;
        int duplicateLabelsAllowed3 = 1;

        int maxSum3 = largestValsFromLabels(values3, labels3, numsVanted3, duplicateLabelsAllowed3);
        System.out.println(maxSum3);
    }

    /*
    Time: O (n log n ) , log n for max Hip soring elements
    Space: O(n)

     where n is number of items in array
     */

    private static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {

        int maxSum = 0;

        Queue<LabelPair> maxHip = new PriorityQueue<>((a, b) -> b.val - a.val);

        for (int i = 0; i < values.length; i++) {
            maxHip.add(new LabelPair(values[i], labels[i]));
        }

        Map<Integer, Integer> labelCounts = new HashMap<>();

        while( numWanted > 0 && !maxHip.isEmpty() ) {

            LabelPair pair = maxHip.poll();//we can use one time hence poll

            labelCounts.put(pair.label, labelCounts.getOrDefault(pair.label, 0) + 1);

            if(labelCounts.get(pair.label) <= useLimit) {//current time of usage is below allowed use limit

                maxSum += pair.val;//aggregate sum

                numWanted--;//we used one element

            }
        }

        return maxSum;
    }



    private static class LabelPair {
        int val;
        int label;
        LabelPair(int val, int label) {
            this.val = val;
            this.label = label;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getLabel() {
            return label;
        }

        public void setLabel(int label) {
            this.label = label;
        }
    }
}
