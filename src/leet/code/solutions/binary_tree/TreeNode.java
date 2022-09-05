package leet.code.solutions.binary_tree;

public class TreeNode<T> {
    public T val;
    public TreeNode left;
    public TreeNode right;


    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode(T val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "\r\n\t TreeNode{" +
            "\r\n val=" + val +
            ",\r\n  left=" + left +
            ",\r\n  right=" + right +
            "}\r\n";
    }


    public String toStringJustNums() {
        return " " + val + " ,";
    }
}


