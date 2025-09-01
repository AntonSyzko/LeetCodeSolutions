package leet.code.solutions.hashing;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@FunctionalInterface
interface HashFunction {

    int hash(Object key);

}

class MD5HashFunction implements HashFunction {
    private final  MessageDigest md;

    public MD5HashFunction() {
        try {
            md = MessageDigest.getInstance("MD5");//SHA256 etc ...
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not available", e);
        }
    }

    @Override
    public int hash(Object key) {

        md.reset();

        byte[] bytes = md.digest(key.toString().getBytes());

        return ByteBuffer.wrap(bytes).getInt();

//        return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) |
//                ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
    }
}

public class ConsistentHash<T> {

    private final HashFunction hashFunction;//interface
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle ;//the very ring

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        this.circle = new TreeMap<>();

        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int currNodesHash = hashFunction.hash(node.toString() + i);
            circle.put(currNodesHash, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int currNodesHash = hashFunction.hash(node.toString() + i);
            circle.remove(currNodesHash);
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = hashFunction.hash(key);

        if (!circle.containsKey(hash)) {  // No node exactly at this position

            SortedMap<Integer, T> tailMap = circle.tailMap(hash);  // Find next nodes clockwise

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
        final int NUMBER_OF_REPLICAS = 3;
        ConsistentHash<String> hash = new ConsistentHash<>(new MD5HashFunction(), NUMBER_OF_REPLICAS, servers);

        hash.printHashRing();

        // Test key distribution
        String[] keys = {"key1", "key2", "key3", "key4", "key5"};

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
       // hash.add("server5");
        for (String key : keys) {
            System.out.println(key + " -> " + hash.get(key));
        }
    }
}