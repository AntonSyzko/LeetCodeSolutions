package leet.code.solutions.binary_tree;


public class LongestConsecutiveSequenceInBinTree {

    public static void main(String[] args) {
          TreeNode root = new TreeNode(1);

          root.left = new TreeNode(2);
          root.right = new TreeNode(22);

          root.left.left = new TreeNode(3);
          root.left.right = new TreeNode(6);

          root.left.left.left = new TreeNode(4);

          int longestSubseq = longestConsecutive(root);
          System.out.println(longestSubseq);
    }

    private static int longestConsecutiveRes= Integer.MIN_VALUE;

    private  static int longestConsecutive(TreeNode root) {

        if(root==null) return 0;

        int startingSequenceLen = 1;//we star tas default increasing subseq is just 1

        findLongestConsecutiveSequence(root, startingSequenceLen);

        return longestConsecutiveRes;
    }


    private static void findLongestConsecutiveSequence(TreeNode node, int res) {

        if(node==null) return;// BASE

        longestConsecutiveRes = Math.max(res, longestConsecutiveRes);

        if(node.left != null ){

              if(node.left.val == node.val + 1){//subsequently increasing

                  findLongestConsecutiveSequence(node.left, res + 1);//increase current sequence len

              }else{

                  findLongestConsecutiveSequence(node.left, 1);//reset as sequence re-starts here
              }

        }

        if(node.right != null ){

            if(node.right.val == node.val + 1){//subsequently increasing

                findLongestConsecutiveSequence(node.right, res + 1);

            }else{

                findLongestConsecutiveSequence(node.right, 1);//reset as sequence re-starts here
            }

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
