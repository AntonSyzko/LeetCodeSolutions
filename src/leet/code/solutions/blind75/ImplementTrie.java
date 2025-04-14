package leet.code.solutions.blind75;

import java.util.Arrays;

/*
https://leetcode.com/problems/implement-trie-prefix-tree/

A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3  104 calls in total will be made to insert, search, and startsWith.
 */
public class ImplementTrie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(  trie.search("apple"));    // returns true
        System.out.println(  trie.search("app"));      // returns false
        System.out.println( trie.startsWith("app"));  // returns true
        trie.insert("app");
        trie.search("app");

    }

     static class Trie {
        private TrieNode root;


        // Inner class for Trie nodes
        static class TrieNode {
            private TrieNode[] children;
            private boolean isEndOfWord;

            public TrieNode() {
                this.children = new TrieNode[26]; // 26 lowercase English letters
                this.isEndOfWord = false;
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a'; // Convert character to index (0-25)

                // If the path doesn't exist, create a new node
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                // Move to the next node
                current = current.children[index];
            }

            // Mark the end of the word after all characters traversed
            current.isEndOfWord = true;
        }

        

        public boolean search(String word) {
            TrieNode node = searchPrefix(word);

            // Return true if node exists and is a complete word
            return node != null && node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            // Just need to check if the prefix path exists in the trie
            return searchPrefix(prefix) != null;
        }

        // Helper method to find a node that matches the given prefix
        private TrieNode searchPrefix(String prefix) {
            TrieNode current = root;

            for (char c : prefix.toCharArray()) {
                int index = c - 'a';

                // If path doesn't exist, return null
                if (current.children[index] == null) {
                    return null;
                }

                // Move to the next node
                current = current.children[index];
            }

            return current;
        }
    }

}


/*
      Example usage:
      Trie trie = new Trie();
      trie.insert("apple");
      trie.search("apple");    // returns true
      trie.search("app");      // returns false
      trie.startsWith("app");  // returns true
      trie.insert("app");
      trie.search("app");      // returns true
 */
 
