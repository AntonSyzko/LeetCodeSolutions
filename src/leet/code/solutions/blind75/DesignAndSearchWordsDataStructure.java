package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/design-add-and-search-words-data-structure/description/

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

 */
public class DesignAndSearchWordsDataStructure {


    class WordDictionary {
        private TrieNode root;

        // TrieNode class for the word dictionary
        private static class TrieNode {
            private TrieNode[] children;
            private boolean isEndOfWord;

            public TrieNode() {
                this.children = new TrieNode[26]; // 26 lowercase English letters
                this.isEndOfWord = false;
            }
        }

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }

            current.isEndOfWord = true;
        }

        public boolean search(String word) {
            return searchInNode(word, 0, root);
        }

        private boolean searchInNode(String word, int index, TrieNode node) {
            // Base case: if we've processed all characters in the word
            if (index == word.length()) {
                return node.isEndOfWord;
            }

            char c = word.charAt(index);

            if (c == '.') {
                // If current character is '.', we need to check all possible paths
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null &&
                            searchInNode(word, index + 1, node.children[i])) {
                        return true;
                    }
                }
                return false;
            } else {
                // Normal character - follow the specific path if it exists
                int charIndex = c - 'a';
                if (node.children[charIndex] == null) {
                    return false;
                }
                return searchInNode(word, index + 1, node.children[charIndex]);
            }
        }
    }

/**
 * Example usage:
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // returns false
 * wordDictionary.search("bad"); // returns true
 * wordDictionary.search(".ad"); // returns true
 * wordDictionary.search("b.."); // returns true
 */
}

/*
Time Complexity


        addWord: O(m) where m is the length of the word

        We process each character once


        search:

        Best case (no wildcards): O(m) where m is the length of the search word
        Worst case (all wildcards): O(26^m) which is exponential

        For each '.', we potentially need to explore all 26 possible characters
        This creates a branching factor of 26 for each wildcard



  Space Complexity

        Storage: O(n*m) where n is the number of words and m is the average word length

        In the worst case, we need to store all characters of all words
        Though there is sharing of prefixes which can reduce space


        Search recursion: O(m) stack space for recursion depth

        The maximum recursion depth is the length of the search word
 */
