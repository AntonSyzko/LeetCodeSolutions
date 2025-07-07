package leet.code.solutions.DS;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
public class LRUCache {

        private final Map<Integer, Integer> map;
        private final Set<Integer> seenKeys;
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>(capacity);
            this.seenKeys = new LinkedHashSet<>(capacity);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {// cache miss
                return -1;
            }

            moveToCacheEnd(key);

            return map.get(key);
        }

    public void put(int key, int value) {
            if (map.containsKey(key)) {
                // Key already exists - update value and move to end
                map.put(key, value);

                moveToCacheEnd(key);

            } else {
                //new key
                if (map.size() == capacity) {//cache capacity exceeded

                    // Remove LRU (first element)
                    int lruKey = seenKeys.iterator().next();
                    map.remove(lruKey);
                    seenKeys.remove(lruKey);

                }

                // Add new key-value pair
                map.put(key, value);

                seenKeys.add(key);
            }
        }

    private void moveToCacheEnd(int key) {
        // Move to end (most recently used)
        seenKeys.remove(key);
        seenKeys.add(key);
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);

        System.out.println(" get 1 : " + lruCache.get(1));

        lruCache.put(3, 3);

        System.out.println(" get 2 : " +  lruCache.get(2));

        lruCache.put(4, 4);

        System.out.println(" get 1 : " +  +lruCache.get(1));
        System.out.println(" get 3 : " +  lruCache.get(3));
        System.out.println(" get 4 : " +  lruCache.get(4));
    }
}