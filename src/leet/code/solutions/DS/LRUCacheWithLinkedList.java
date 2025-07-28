package leet.code.solutions.DS;

import java.util.HashMap;
import java.util.Map;

/*
146

https://leetcode.com/problems/lru-cache/description/

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 */
public class LRUCacheWithLinkedList {

    // Node class for doubly linked list
    private class Node {
        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, Node> map;
    private final int capacity;

    // Dummy head and tail nodes to simplify edge cases
    private final Node head;
    private final Node tail;

    public LRUCacheWithLinkedList(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);

        this.head = new Node(-1, -1);//dummies
        this.tail = new Node(-1, -1);

        head.next = tail;//nothing is yet in the linked list
        tail.prev = head;//hence they point to each other
    }

    private  int get(int key) {

            Node node = map.get(key);

            if (node == null) {
                return -1;
            }

            moveToHead(node);

            return node.value;
    }

    private void put(int key, int value) {

            //Check if key exists first
            if (map.containsKey(key)) {

                Node existingNode = map.get(key);
                existingNode.value = value;//set updated value
                moveToHead(existingNode);
                return; // CRITICAL: Must return here to avoid executing new key logic
            }

            Node newNode = new Node(key, value);

            if (map.size() >= capacity) {//capacity limit hit
                Node fromTail = tail.prev;//last node in Linked list is the one at the tail ( tail itself is dummy - so it's prev actually is the last)

                removeNodeFromLinkedList(fromTail);

                map.remove(fromTail.key);
            }

            map.put(key, newNode);
            addNodeToHead(newNode);
    }

    // Helper method to add node right after head
    private void moveToHead(Node node) {
        removeNodeFromLinkedList(node);
        addNodeToHead(node);
    }

    private void addNodeToHead(Node newNode) {
        newNode.prev = head;
        newNode.next = head.next;

        head.next.prev = newNode;
        head.next = newNode;
    }

    // Helper method to remove a node from the list
    private void removeNodeFromLinkedList(Node nodeToRemove) {
        Node prev = nodeToRemove.prev;
        Node next = nodeToRemove.next;

        prev.next = next;
        next.prev = prev;
    }

    public static void main(String[] args) {
        LRUCacheWithLinkedList cache = new LRUCacheWithLinkedList(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(" get key 1: " + cache.get(1)); // returns 1

        System.out.println("add key 3 over limit of 2 allowed in cache");
        cache.put(3, 3); // evicts key 2
        System.out.println("get 2 : " + cache.get(2)); // returns -1 (not found)

        System.out.println("add key 4 over limit of 2 allowed in cache");
        cache.put(4, 4); // evicts key 1
        System.out.println(" get 1: " + cache.get(1)); // returns -1 (not found)
        System.out.println(" get 3: " + cache.get(3)); // returns 3
        System.out.println(" get 4: " + cache.get(4)); // returns 4
    }
}
