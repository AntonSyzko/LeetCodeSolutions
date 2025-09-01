package leet.code.solutions.backtracking;

import java.util.Stack;

/*
The challenge is to determine whether a given array of unique integers represents the correct preorder traversal of a Binary Search Tree (BST). Preorder traversal of a
BST means visiting the nodes in the order: root, left, right. To qualify as a valid BST traversal,
the sequence must reflect the BST's structure, where for any node, all the values in the left subtree are less than the node's value, and all the values in the right subtree are greater.
 */
public class VerifyPreorderOfBinSearchTree {

    public static void main(String[] args) {
        int[] preorder = new int[]{5, 2, 1, 3, 6};

        System.out.println(verifyPreorder(preorder));

    }

    public static boolean verifyPreorder(int[] preorder) {
     Stack<Integer> stack = new Stack<>();

     int lastProcessedVal = Integer.MIN_VALUE;

     for (int preorderVal : preorder) {

         if(preorderVal < lastProcessedVal){
             System.out.println("tripped at " + preorderVal + " compared to last processed " + lastProcessedVal);
             return false;
         }

         while (!stack.isEmpty() && stack.peek() < preorderVal){
             lastProcessedVal = stack.pop();
         }

         stack.push(preorderVal);
     }

     return true;
    }


    private  static int INDEX ;

    public static boolean verifyPreorder2(int[] preorder) {
        INDEX = 0;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

         verifyDFS(preorder,min, max);

         return INDEX == preorder.length;

    }

    private  static  void verifyDFS(int[] preorder, int min, int max) {
        if(INDEX == preorder.length ){
            return;
        }

        if(preorder[INDEX] < min || preorder[INDEX] > max){
            return ;
        }

         int val = preorder[INDEX++];

        verifyDFS(preorder,min,val);
        verifyDFS(preorder,val,max);
    }

}