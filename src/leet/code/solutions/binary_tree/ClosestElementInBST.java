package leet.code.solutions.binary_tree;

/*
https://www.callicoder.com/find-closest-element-binary-search-tree/

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Example
-----
Binary Search Tree:
     9
   /   \
  4      17
 / \      \
3   6      22
   / \     /
  5   7   20

target: 18

Output
17
 */
public class ClosestElementInBST {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(9);
        node.left = new TreeNode(4);
        node.right = new TreeNode(17);

        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(6);
        node.left.right.left = new TreeNode(5);
        node.left.right.right = new TreeNode(7);

        node.right.right = new TreeNode(22);
        node.right.right.left = new TreeNode(20);

        System.out.println(findClosestValue(node, 18));
    }

    /*
    Time Complexity
            O(h) where h is the height of the BST:

            In the worst case (skewed tree), h = n (number of nodes)
            In a balanced BST, h = log(n)

            The algorithm makes at most one traversal from root to leaf, visiting only O(h) nodes in the process.
Space Complexity
             O(1) - You're using a constant amount of extra space regardless of input size (just the variables to track closest value and minimum difference).
     */

    private static int findClosestValue(TreeNode node, int target) {
        int closestValue = node.val;//at start set closes to current node val
        int minDifferenceToTarget = Integer.MAX_VALUE;

        while(node != null){

            int currentDiffToTarget = Math.abs(target - node.val);//mind ABS, we care about any distance closest

            if(currentDiffToTarget < minDifferenceToTarget){
                minDifferenceToTarget = currentDiffToTarget;//reset min
                closestValue = node.val;//reset closest
            }

            if(target < node.val){//current node value is too big

                node = node.left;

            }else if ( target > node.val ){ // current node val is too small

                node = node.right;

            }else{

               break; // curr node value is found

            }
        }

        return closestValue;//return whatever was assigned as we went through while loop
    }

    //-------------------- RECURSIVE-------------------

    private static int findClosestValueRec(TreeNode root, int target) {
        return findClosestValueHelper(root, target, root.val);
    }

    private static int findClosestValueHelper(TreeNode node, int target, int closestValue) {
        if (node == null) {//BASE
            return closestValue;
        }

        // Update closest if current node is closer to target
        int currentDiffToTarget = Math.abs(target - node.val);
        int closestDiffToTarget = Math.abs(target - closestValue);

        if (currentDiffToTarget < closestDiffToTarget) {
            closestValue = node.val;
        }

        // If exact match found, return immediately
        if (node.val == target) {
            return node.val;
        }

        // Traverse left or right subtree based on BST property
        if (target < node.val) {
            return findClosestValueHelper(node.left, target, closestValue);
        } else {
            return findClosestValueHelper(node.right, target, closestValue);
        }
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
