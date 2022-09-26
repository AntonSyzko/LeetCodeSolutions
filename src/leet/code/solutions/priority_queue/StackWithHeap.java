package leet.code.solutions.priority_queue;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class StackWithHeap {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private static class ValueWithRank {
        public Integer value;
        public Integer rank;//acts as a timestamp , later addedd - higher rank

        public ValueWithRank(Integer value, Integer rank){
            this.value = value;
            this.rank = rank;
        }
    }

    private static class Compare implements Comparator<ValueWithRank> {
        @Override
        public int compare(ValueWithRank ol, ValueWithRank o2){
            return Integer.compare(o2.rank, ol.rank);
        }
        public static final Compare COMPARE_VALUEWITHRANK = new Compare();
    }

    public static class Stack {
        private int timestamp = 0;

        //higher rank on top, so later ranked added on top - LIFO - later ones will be popped first
        private PriorityQueue<ValueWithRank> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, Compare.COMPARE_VALUEWITHRANK);

        public void push(Integer x){
            maxHeap.add(new ValueWithRank(x, timestamp++));
        }

        public Integer pop() throws NoSuchElementException {
            return maxHeap.remove().value;
        }
        public Integer peek(){
            return maxHeap.peek().value;
        }
    }
}
