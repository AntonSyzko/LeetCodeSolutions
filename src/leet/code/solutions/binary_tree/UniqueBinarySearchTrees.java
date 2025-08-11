package leet.code.solutions.binary_tree;

public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        int n = 3;

        int res = numTrees(n);
        System.out.println(res);

    }

    /*
    O(n^2)
    O(n)
     */
    private  static int numTrees(int n) {
        // dp[i] := the number of unique BST's that store values 1..i
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int outer = 2; outer <= n; ++outer) {

            for (int inner = 0; inner < outer; ++inner) {

                dp[outer] += dp[inner]
                        * //multiply
                        dp[outer - inner - 1];

            }
        }

        return dp[n];
    }
}