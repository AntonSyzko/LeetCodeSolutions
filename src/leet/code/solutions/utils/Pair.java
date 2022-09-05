package leet.code.solutions.utils;


public class Pair<U, V> {
    public final U key;       // first field of a pair
    public final V value;      // second field of a pair

    // Constructs a new Pair with specified values
    public Pair(U key, V value) {
        this.key = key;
        this.value = value;
    }

    public U getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
            "key=" + key +
            ", value=" + value +
            '}';
    }
}
