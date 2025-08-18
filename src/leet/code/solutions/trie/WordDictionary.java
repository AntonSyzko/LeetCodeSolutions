package leet.code.solutions.trie;

/*
211

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


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.
 */
public class WordDictionary {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }


   private  final TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for(char ch : word.toCharArray()) {


            int index = ch -'a';

            if(node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        int letterIndex = 0;//start with first letter
       return dfs(word, letterIndex, node );
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if(index == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(index);
        int indexOfCurrCharInTrieChildren = ch - 'a';

        if(ch != '.'){
            TrieNode next = node.children[indexOfCurrCharInTrieChildren];
            return next != null && dfs(word, index + 1, next);
        }

        for(int i = 0; i < 26; i++){


            TrieNode next = node.children[i];

            if(next != null //if there is a TrieNode for such a letter at ALL
                    && dfs(word, index + 1, next)){//+1 just iterates over all letters 26

                return true;
            }

        }
        return false;
    }

    private static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isEnd;
    }
}
