package leet.code.solutions.blind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/word-search-ii/

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
Input: board = [['o','a','a','n'],['e','t','a','e'],['i','h','k','r'],['i','f','l','v']], words = ['oath','pea','eat','rain']
Output: ['eat','oath']

Example 2:
Input: board = [['a','b'],['c','d']], words = ['abcb']
Output: []

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
 */
public class WordSearch2 {


    public static void main(String[] args) {
        char [][] board = {{'o','a','a','n'},
                          {'e','t','a','e'},
                          {'i','h','k','r'},
                          {'i','f','l','v'}};

        String[] words =  {"oath","pea","eat","rain"};
        Solution solution = new Solution();

        List<String> res = solution.findWords(board, words);
        System.out.println(res);


    }

   static class Solution {
        // Result list to store all found words
        List<String> result = new ArrayList<>();

        // Directions for searching adjacent cells: up, right, down, left
        int[][] dirs = {{-1, 0}, //up row
                        {0, 1}, //  right col
                        {1, 0}, //down row
                        {0, -1}};//left col

        public List<String> findWords(char[][] board, String[] words) {
            // Edge cases
            if (board == null || board.length == 0 || words == null || words.length == 0) {
                return result;
            }

            // Build trie with all words
            TrieNode root = buildTrie(words);

            int ROWS = board.length;
            int COLS = board[0].length;

            boolean[][] visited = new boolean[ROWS][COLS];

            // Search starting from each cell in the board
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    dfs(board, row, col, root, visited);
                }
            }

            return result;
        }

        // DFS to explore the board
        private void dfs(char[][] board, int row, int col, TrieNode node, boolean[][] visited) {
            // Check boundaries and visited status
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
                return;
            }

            char currentChar = board[row][col];

            // If current character is not in the trie, stop exploration
            if (!node.children.containsKey(currentChar)) {
                return;
            }

            // Move to next node in trie
            node = node.children.get(currentChar);
            visited[row][col] = true;

            // If we've found a complete word, add it to result
            if (node.word != null) {//if during all recursive explorations we hit the node.word - so full match of the entire word - add to res
                result.add(node.word);
                node.word = null; // Prevent duplicate findings
            }

            // Explore in all four directions
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                dfs(board, newRow, newCol, node, visited);
            }

            // Backtrack
            visited[row][col] = false;
        }

        // Build trie from array of words
        private TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();

            for (String word : words) {
                TrieNode node = root;

                for (char c : word.toCharArray()) {
                    if (!node.children.containsKey(c)) {
                        node.children.put(c, new TrieNode());
                    }

                    node = node.children.get(c);//move to next
                }

                node.word = word; // Store the complete word at the end node
            }

            return root;
        }

        // HashMap-based Trie Node implementation
        static class TrieNode {
            Map<Character, TrieNode> children;
            String word; // Store the complete word at the end node

            public TrieNode() {
                children = new HashMap<>();
                word = null;
            }
        }
    }
}
