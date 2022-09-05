package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/find-largest-value-in-each-tree-row/
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:
Input: root = [1,2,3]
Output: [1,3]

Constraints:
The number of nodes in the tree will be in the range [0, 104].
-231 <= Node.val <= 231 - 1
 */
public class LargestValueInEachRow {
    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootLeft = new  TreeNode<Integer>(3);
        TreeNode<Integer> rootRight = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootLeftLeft = new  TreeNode<Integer>(5);
        TreeNode<Integer> rootLeftRight = new  TreeNode<Integer>(3);
        TreeNode<Integer> rootRightRight = new  TreeNode<Integer>(9);

        root.left = rootLeft;
        root.right = rootRight;
        rootLeft.left = rootLeftLeft;
        rootLeft.right = rootLeftRight;
        rootRight.right = rootRightRight;

        List<Integer> res = largestValues(root);
        System.out.println(res);

    }

    public static List<Integer> largestValues( TreeNode<Integer> root) {
     List<Integer> largestLevelVals = new ArrayList<>();

        addLargest(root, largestLevelVals, 0);

        return largestLevelVals;

    }

    private static void addLargest( TreeNode<Integer> root, List<Integer> largestRowValues, int level) {
        if(root==null){//recursion exit base
            return;
        }

        if(level == largestRowValues.size()){//start of level
            largestRowValues.add(root.val);//just add first node whatever that is
        }else{
            //set at the level          max of previously set and current node val
            largestRowValues.set(level, Math.max(largestRowValues.get(level), root.val));//overrides prev stored val to max met at this level
        }
        //recursion
        addLargest(root.left, largestRowValues, level+1);//left and level down
        addLargest(root.right, largestRowValues, level+1);//right and level down

    }

}
