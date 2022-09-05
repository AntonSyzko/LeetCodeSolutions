package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/maximum-depth-of-n-ary-tree/

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
public class MaxDepthOfN_AryTree {

    public static void main(String[] args) {
    }


    private static int dfs_MAx_Depth = 0;

    private static int maxDepthDFS(Node root) {
        if(root==null) return 0;

        findMaxDepthDFS(root,1);//1 since root is a level

        return dfs_MAx_Depth;
    }

    private static void findMaxDepthDFS(Node node, int currentLevelCount) {
        if(node == null) return;//recursion base ( void )


        dfs_MAx_Depth = Math.max(dfs_MAx_Depth, currentLevelCount);//max current

        for (Node child : node.children){
            //1 means each node in LEVEL is just one count
            findMaxDepthDFS(child,currentLevelCount +1 );//goes as deep as node has children and it's children has recursively children
        }
    }


    private static int maxDepthBFS(Node root) {
        if (root == null) {
            return 0;
        }

        int resDepth = 0;

        Queue<Node> queue = new LinkedList<>();//BFS queue
        queue.offer(root);

        while (!queue.isEmpty()) {

            int currentSize = queue.size();//current size of ALL nodes in LEVEL

            for (int i = 0; i < currentSize; i++) {//traverse ALL nodes in LEVEL

                Node currentNode = queue.poll();//get EACH node in LEVEL

                for (Node child : currentNode.children) {//add each child to Queue, this will cover all children in LEVEL ( since will be added for each node in LEVEL)
                    queue.add(child);
                }
            }//and this completes the level traversal

            resDepth++;//increase LEVEL  DEPTH count
        }
        return resDepth;
    }
    }

