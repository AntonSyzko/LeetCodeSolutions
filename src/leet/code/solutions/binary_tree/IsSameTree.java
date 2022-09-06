package leet.code.solutions.binary_tree;

public class IsSameTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        TreeNode<Integer> rootLeft = new TreeNode<Integer>(2);
        TreeNode<Integer> rootRight = new TreeNode<Integer>(3);
        root.left = rootLeft;
        root.right = rootRight;

        TreeNode<Integer> rootCopy = root;

        boolean isSameTree = isSameTree(root, rootCopy);
        System.out.println(isSameTree);
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
       if(p == null && q == null) return true;
       if(p == null || q == null) return false;
       if(p.val != q.val) return false;

       return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private static boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
