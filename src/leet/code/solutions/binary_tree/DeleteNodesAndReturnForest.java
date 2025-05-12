package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/delete-nodes-and-return-forest/description/

Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.


Example 1:

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:

Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int[] toDelete = {3,5};
        List<TreeNode> afterDel = delNodes(root,toDelete );
        System.out.println(afterDel);
    }

    /*
    Big O Complexity
            Time Complexity: O(n)

            Converting to_delete array to HashSet: O(m) where m is the length of to_delete
            Tree traversal: O(n) where n is the number of nodes in the tree
            HashSet lookups during traversal: O(1) per node
            Total: O(n + m), but since m ≤ n (constraint: to_delete.length <= 1000 and max nodes is 1000), this simplifies to O(n)

            Space Complexity: O(n)

            HashSet for to_delete: O(m) ≤ O(n)
            Recursive call stack: O(h) where h is the height of the tree

            In the worst case (skewed tree), h = n, so O(n)
            In the best case (balanced tree), h = log(n)


            Result list: O(n) in the worst case (if we delete all internal nodes)
            Total: O(n)
     */

    private static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> remainingNodes = new ArrayList<>();
        if (root == null) {
            return remainingNodes;
        }

        Set<Integer> delSet = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());

        // Post-order traversal and collect roots
        TreeNode newRoot = removeNode(root, delSet, remainingNodes);

        // If the original root wasn't deleted, it's also a root of a remaining tree
        if (newRoot != null) {
            remainingNodes.add(newRoot);
        }

        return remainingNodes;
    }

    private static TreeNode removeNode(TreeNode node, Set<Integer> delSet, List<TreeNode> remainingNodes) {
        if(node==null) {//BASE
            return null;
        }

        node.left = removeNode(node.left, delSet, remainingNodes);
        node.right = removeNode(node.right, delSet, remainingNodes);

        if(delSet.contains(node.val)) {//if current node is to delete

            if(node.left != null) {//and it has left child
                remainingNodes.add(node.left);//keep it
            }

            if(node.right != null) {
                remainingNodes.add(node.right);
            }

            return null;//substitute current node with null as we delete it
        }

        return node;//just return node as it is ( it was not for deletion )
    }



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
