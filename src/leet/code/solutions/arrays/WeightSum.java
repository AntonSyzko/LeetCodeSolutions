package leet.code.solutions.arrays;

import java.util.List;

/*
https://leetcode.com/problems/nested-list-weight-sum/

Problem:
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27)
 */
public class WeightSum {
    public static void main(String[] args) {

    }

    private static int getDepthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private static int dfs(List<NestedInteger> nestedList, int currentLevel) {
        int sum = 0;

        for (NestedInteger each : nestedList) {
            if (each.isInteger()) {
                sum += currentLevel * each.getInteger();
            } else {
                sum += dfs(each.getList(), currentLevel + 1);
            }
        }

        return sum;
    }


    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }
}
