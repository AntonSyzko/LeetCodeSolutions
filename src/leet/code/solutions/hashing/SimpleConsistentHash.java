package leet.code.solutions.hashing;

import java.util.*;

public class SimpleConsistentHash<T> {
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<>();

    public SimpleConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            // More robust approach: hash the combination of node hash and replica number
            int hash = (node.toString() + i).hashCode();
            // Apply additional mixing to break up clustering
            hash = hash ^ (hash >>> 16);
            hash = hash * 0x9e3779b9; // Golden ratio constant for better distribution

            circle.put(hash, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {

            int hash = (node.toString() + i).hashCode();

            hash = hash ^ (hash >>> 16);
            hash = hash * 0x9e3779b9;

            circle.remove(hash);
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = key.hashCode();

        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }

        return circle.get(hash);
    }

    // Helper method to visualize the hash ring
    public void printHashRing() {
        System.out.println("Hash Ring:");
        for (Map.Entry<Integer, T> entry : circle.entrySet()) {
            System.out.println("  Hash: " + entry.getKey() + " -> Node: " + entry.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test with server nodes
        List<String> servers = Arrays.asList("server1", "server2", "server3");
        SimpleConsistentHash<String> hash = new SimpleConsistentHash<>(3, servers);

        hash.printHashRing();

        // Test key distribution with more diverse keys
        String[] keys = {"apple", "banana", "cherry", "database", "elephant",
                "frontend", "guitar", "hardware", "internet", "javascript"};

        System.out.println("Key distribution:");
        for (String key : keys) {
            System.out.println(key + " -> " + hash.get(key));
        }

        // Test node removal
        System.out.println("\nAfter removing server2:");
        hash.remove("server2");
        for (String key : keys) {
            System.out.println(key + " -> " + hash.get(key));
        }

        // Test node addition
        System.out.println("\nAfter adding server4:");
        hash.add("server4");
        for (String key : keys) {
            System.out.println(key + " -> " + hash.get(key));
        }
    }
}