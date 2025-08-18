package leet.code.solutions.trie;


public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("banana");

        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("ban"));

        System.out.println(trie.search("pear"));
        System.out.println(trie.startsWith("pl"));
    }

    /*
    Time
    insert O(∣word∣)
    search O(∣word∣)
    startsWith O(∣prefix∣)

    Space :
    O(Σ∣words[i]∣)
     */
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

   private  TrieNode root = new TrieNode();

    public   void insert(final String word) {
        TrieNode node = root;//always start from root of a Trie

         for(char ch : word.toCharArray()) {
             int childIndex = ch - 'a';

             if(node.children[childIndex] == null) {
                 node.children[childIndex] = new TrieNode();
             }

             node = node.children[childIndex];//shift to next node
         }

         node.isEnd = true;
    }


    public  boolean search(final String word) {
         TrieNode node = searchPrefix(word);
         return node != null && node.isEnd;
    }


    public  boolean startsWith(final String prefix) {
         TrieNode node = searchPrefix(prefix);
         return node != null ;

    }

    private TrieNode searchPrefix(final String prefix) {
        TrieNode node = root;//start with root always

        for(char ch : prefix.toCharArray()) {

            int childIndex = ch - 'a';

            if(node.children[childIndex] == null) {
                return null;
            }

            node = node.children[childIndex];
        }

        return node;
    }
}