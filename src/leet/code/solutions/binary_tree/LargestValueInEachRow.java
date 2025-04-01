package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        res = findLargestBFS(root);
        System.out.println(res);

    }

    public static List<Integer> largestValues( TreeNode<Integer> root) {
     List<Integer> largestLevelVals = new ArrayList<>();

        addLargest(root, largestLevelVals, 0);

        return largestLevelVals;

    }

    //DFS
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

    private static List<Integer> findLargestBFS( TreeNode<Integer> root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<>();//BFS queue
        queue.add(root);

        while(!queue.isEmpty()){

            int currentRowMax = Integer.MIN_VALUE;

            int currentSize = queue.size();//it's number of ALL elements in row

            for(int i = 0; i < currentSize; i++) {// iterate for as many times as elements in this list - size in list will traverse all level elements

                TreeNode node = queue.removeFirst();// MIND remove FIRST
                currentRowMax = Math.max(currentRowMax, (Integer) node.val);

                if(node.left != null) queue.addLast(node.left);//it will insert in list but for future traversals, in this traversal we iterate = 'currentSize' over elements previously stored
                if(node.right != null) queue.addLast(node.right);// mind ADD LAST

            }
            //we have traversed all nodes of level , since we extracted nodes currentSize time from list , just store max
            result.add(currentRowMax);
        }

        return result;
    }

}
