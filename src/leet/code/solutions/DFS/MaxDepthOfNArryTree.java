package leet.code.solutions.DFS;

import java.util.List;

/*
559
https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/

Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Example 1:

Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Example 2:

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 5

Constraints:

The total number of nodes is in the range [0, 104].
The depth of the n-ary tree is less than or equal to 1000.
 */
public class MaxDepthOfNArryTree {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node h1 = new Node(2);
        Node h2 = new Node(3);
        Node h3 = new Node(4);
        Node h4 = new Node(5);

        List<Node> hChildren = List.of(h1, h2);
        List<Node> h1Children = List.of(h3);
        List<Node> h3Children = List.of(h4);
        head.children = hChildren;
        h1.children = h1Children;
        h3.children = h3Children;

        int maxDepthDFS = maxDepthDFS(head);
        System.out.println(maxDepthDFS);
    }

    private static int maxDepthDFS(Node root) {
        if(root==null) return 0;

        int[] maxDepth = new int[1];//pass by value
        int currDepth = 0;

        dfs(root,currDepth, maxDepth);

        return maxDepth[0];
    }


    private static void dfs(Node node,int currDepth, int[] maxDepth) {

        if(node==null) return;

        currDepth++;//level increased

        maxDepth[0]= Math.max(maxDepth[0],currDepth);

        if(node.children == null){
            return;
        }

        for(Node child : node.children){
            dfs(child, currDepth, maxDepth);
        }
    }



   private static   class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
