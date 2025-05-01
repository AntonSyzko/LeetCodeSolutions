package leet.code.solutions.binary_tree;


public class ConvertSOrtedArrayToBST {


    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};

        TreeNode bst = sortedArrayToBST(nums);
        System.out.println(bst);

    }
    
    private static TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;


        return constructBST(nums, 0, nums.length -1);
    }


    private static TreeNode constructBST(int[] nums, int left, int right){


        if(left > right ) return null;

        int mid = left + ( right - left)/2;

        TreeNode curr = new TreeNode(nums[mid]);

        curr.left = constructBST(nums, left, mid -1);
        curr.right = constructBST(nums, mid +1, right);

        return curr;
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
