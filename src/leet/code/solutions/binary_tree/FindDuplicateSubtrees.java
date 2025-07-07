package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
652

https://leetcode.com/problems/find-duplicate-subtrees/description/

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

Example 1:

Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:

Input: root = [2,1,1]
Output: [[1]]
 */
public class FindDuplicateSubtrees {

    public static void main(String[] args) {

    }

    /*
    Time & Space Complexity
        Time Complexity: O(N²)

                We visit each node once: O(N)
                For each node, we create a serialization string: O(N) in worst case
                Overall: O(N²)

        Space Complexity: O(N²)

                HashMap storage: O(N) entries, each string can be O(N) length
                Recursion stack: O(H) where H is tree height
                Overall: O(N²)
     */

    private static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();

        Map<String, Integer> subtreeCount = new HashMap<>();

        serialize(root, subtreeCount, result);

        return result;
    }

    private  static String serialize(TreeNode node, Map<String, Integer> subtreeCount, List<TreeNode> result) {
        if(node == null){//BASE
            return "null";
        }

        // Serialize left and right subtrees first (post-order)
        String leftSerialisation =  serialize(node.left, subtreeCount, result);
        String rightSerialisation =  serialize(node.right, subtreeCount, result);

        // Create serialization for current subtree
        String currentNodeSerialisation = node.val + "," + leftSerialisation + "," + rightSerialisation;

        // Track count of this serialization
        int prevSeenCount = subtreeCount.getOrDefault(currentNodeSerialisation,0);
        subtreeCount.put(currentNodeSerialisation, prevSeenCount + 1);

        // If we've seen this subtree exactly twice, add it to result (We only want to add it once, not every time we see it)
        if(subtreeCount.get(currentNodeSerialisation) == 2){
             result.add(node);
        }

        return currentNodeSerialisation;
    }

        // TreeNode definition (provided in problem)
    private static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
}