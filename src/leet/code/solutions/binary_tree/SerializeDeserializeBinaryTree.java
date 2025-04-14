package leet.code.solutions.binary_tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

    Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

    Example 1:
    Input: root = [1,2,3,null,null,4,5]
    Output: [1,2,3,null,null,4,5]

    Example 2:
    Input: root = []
    Output: []

    Constraints:

    The number of nodes in the tree is in the range [0, 104].
    -1000 <= Node.val <= 1000
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) {
                  /*
                            1
                          2   3
                       4
                   */
         TreeNode root = new TreeNode(1);
         root.left = new TreeNode(2);
         root.right = new TreeNode(3);
         root.left.left = new TreeNode(4);

        System.out.println("\r\n\t tree before serialisation ");
        System.out.println(root);

         String serialisedTree = serialize(root);
        System.out.println("\r\n\t serialised tree");
         System.out.println(serialisedTree);

        System.out.println("\r\n\t tree after deserialsiation");
         TreeNode deserialsied = deserialize(serialisedTree);
         System.out.println(deserialsied);
    }

    /*
    Time Complexity: O(n) for both serialization and deserialization, where n is the number of nodes in the tree. We visit each node exactly once.
Space Complexity: O(n) for the serialized string and the recursion stack in the worst case (highly unbalanced tree).
     */

    // Encodes a tree to a single string.
    private static String serialize(TreeNode root) {
        if(root==null) return "";

        StringBuilder sb = new StringBuilder();

        recursivelySerialise(root,sb);

        return sb.toString();

    }

    private static void recursivelySerialise(TreeNode node, StringBuilder serializationResult) {
        if(node == null){
            serializationResult.append("null").append(",");
            return;
        }

        serializationResult.append(node.val).append(",");

          recursivelySerialise(node.left, serializationResult);
          recursivelySerialise(node.right, serializationResult);

    }

    // Decodes your encoded data to tree.
    public  static TreeNode deserialize(String data) {
        if(data==null || data.isEmpty()) return null;

        List<String> listOfNodeVals = new LinkedList<>(Arrays.asList(data.split(",")));

        TreeNode deserialised = recursivelyDeserialize(listOfNodeVals);

        return deserialised;

    }

    private static  TreeNode recursivelyDeserialize(List<String> listOfNodeVals) {
        if(listOfNodeVals.getFirst().equals("null")) {//if null is string
            listOfNodeVals.removeFirst();//just drop it
            return null;
        }

        TreeNode currentRoot = new TreeNode(Integer.parseInt(listOfNodeVals.getFirst()));
        listOfNodeVals.removeFirst();//after we constructed TeeNode - drop the string from list of strings

        //order of recursive calls determines how the tree will be constructed, lefts first , rights after
        currentRoot.left = recursivelyDeserialize(listOfNodeVals);
        currentRoot.right = recursivelyDeserialize(listOfNodeVals);

        return currentRoot;
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


