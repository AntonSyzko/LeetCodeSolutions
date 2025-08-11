package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {

    public static void main(String[] args) {

    }

    private static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> comboPAth = new ArrayList<>();
        dfs(root, sum, comboPAth, ans);
        return ans;
    }

    private static void dfs(TreeNode root, int sum, List<Integer> comboPath, List<List<Integer>> ans) {
        if (root == null) {//BASE
            return;
        }

        //root.val == sum -> actual equality check / the last element is exact difference to make 0 zero sum - hence true ( it's like to our zero 1 is left - and current IS 1
        if (root.val == sum && root.left == null && root.right == null) {

            comboPath.add(root.val);
            ans.add(new ArrayList<>(comboPath));

            comboPath.remove(comboPath.size() - 1);//BACKTRACK

            return;

        }

        comboPath.add(root.val);

        dfs(root.left, sum - root.val, comboPath, ans);
        dfs(root.right, sum - root.val, comboPath, ans);

        comboPath.remove(comboPath.size() - 1);//backtrack
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