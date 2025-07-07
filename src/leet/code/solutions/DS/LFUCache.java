package leet.code.solutions.DS;

import java.util.*;

/*
460

https://leetcode.com/problems/lfu-cache/description/

Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
 */
public class LFUCache {

    private final int capacity;
    private int minFrequency;

    // key -> value mapping
    private final Map<Integer, Integer> keyToValue;

    // key -> frequency mapping
    private final Map<Integer, Integer> keyToFrequency;

    // frequency -> LinkedHashSet of keys (maintains insertion order for LRU within same frequency)
    private final Map<Integer, LinkedHashSet<Integer>> frequencyToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 1;
        this.keyToValue = new HashMap<>();
        this.keyToFrequency = new HashMap<>();
        this.frequencyToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToValue.containsKey(key)) {
            return -1;
        }

        // Increment frequency and update data structures
        updateFrequency(key);

        return keyToValue.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (keyToValue.containsKey(key)) {
            // Key already exists - update value and frequency
            keyToValue.put(key, value);
            updateFrequency(key);
        } else {
            // New key
            if (keyToValue.size() >= capacity) {
                // Remove LFU (and LRU among LFU) key
                evictLFU();
            }

            // Add new key with frequency 1
            keyToValue.put(key, value);
            keyToFrequency.put(key, 1);
            frequencyToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFrequency = 1;
        }
    }

    private void updateFrequency(int key) {
        int oldFreq = keyToFrequency.get(key);
        int newFreq = oldFreq + 1;

        // Update key's frequency
        keyToFrequency.put(key, newFreq);

        // Remove key from old frequency set
        frequencyToKeys.get(oldFreq).remove(key);

        // If this was the last key with minFrequency, increment minFrequency
        if (oldFreq == minFrequency && frequencyToKeys.get(oldFreq).isEmpty()) {
            minFrequency++;
        }

        // Add key to new frequency set
        frequencyToKeys.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(key);
    }

    private void evictLFU() {
        // Get the LRU key from the minimum frequency set
        LinkedHashSet<Integer> minFreqKeys = frequencyToKeys.get(minFrequency);
        int keyToEvict = minFreqKeys.iterator().next();

        // Remove from all data structures
        minFreqKeys.remove(keyToEvict);
        keyToValue.remove(keyToEvict);
        keyToFrequency.remove(keyToEvict);
    }


    // Test class with the provided example
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);   // cache: {1=1}, frequencies: {1: [1]}
        cache.put(2, 2);   // cache: {1=1, 2=2}, frequencies: {1: [1,2]}
        System.out.println(cache.get(1)); // 1, frequencies: {1: [2], 2: [1]}
        cache.put(3, 3);   // evict key 2, cache: {1=1, 3=3}, frequencies: {1: [3], 2: [1]}
        System.out.println(cache.get(2)); // -1 (not found)
        System.out.println(cache.get(3)); // 3, frequencies: {2: [1,3]}
        cache.put(4, 4);   // evict key 1, cache: {3=3, 4=4}, frequencies: {1: [4], 2: [3]}
        System.out.println(cache.get(1)); // -1 (not found)
        System.out.println(cache.get(3)); // 3, frequencies: {1: [4], 3: [3]}
        System.out.println(cache.get(4)); // 4, frequencies: {2: [4], 3: [3]}

        // Expected output: 1, -1, 3, -1, 3, 4
    }
}

