package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.List;

/*
https://www.techiedelight.com/print-all-paths-from-leaf-to-root-binary-tree/

Given a binary tree, write a recursive algorithm to print all paths from every leaf node to root node in the binary tree.

For example, consider the following binary tree:

 Print Leaf to Root Path

 There are five leaf-to-root paths in the above binary tree:

4 —> 2 —> 1
5 —> 2 —> 1
8 —> 6 —> 3 —> 1
9 —> 6 —> 3 —> 1
7 —> 3 —> 1
 */
public class PrintAllPathFromLeafToRoot {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        List<List<TreeNode>> res = printAllPaths(root);

        for (List<TreeNode> l : res) {
            for (TreeNode node : l) {
                System.out.print(node.val + " -> ");
            }
            System.out.println();
        }

    }

    private static List<List<TreeNode>> printAllPaths(TreeNode root) {

        List<List<TreeNode>> res = new ArrayList<>();
        List<TreeNode> combo = new ArrayList<>();

        printPath(root, combo, res);

        return res;

    }

    private static void printPath(TreeNode node, List<TreeNode> out, List<List<TreeNode>> res) {
        if (node == null) {//BASE
            return;
        }

        out.add(node);


        if(node.left == null && node.right == null){//LEAF

            res.add(new ArrayList<>(out.reversed()));//remove reversed and will be ROOT to LEAF
        }

        printPath(node.left, out, res);
        printPath(node.right, out, res);

        out.removeLast();//backtrack
    }
}
