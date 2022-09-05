package leet.code.solutions.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public  class QueueWithMax<T extends Comparable<T>> {
    private Queue<T> entries = new LinkedList<>();
    private Deque<T> candidatesForMax = new LinkedList<>();

    public void enqueue(T x) {
        entries.add(x);
        while (!candidatesForMax.isEmpty()) {
            // Eliminate dominated elements in candidatesForMax.
            if (candidatesForMax.getLast().compareTo(x) >= 0) {
                break;
            }
            candidatesForMax.removeLast();
        }
        candidatesForMax.addLast(x);
    }


    public T dequeue() {
        if (!entries.isEmpty()) {
            T result = entries.remove();
            if (result.equals(candidatesForMax.getFirst())) {
                candidatesForMax.removeFirst();
            }
            return result;
        }
        throw new NoSuchElementException("Called dequeue  Q on empty queue.");
    }


        public T max() {
            if (!candidatesForMax.isEmpty()) {
                return candidatesForMax.getFirst();
            }
            throw new NoSuchElementException("empty queue");
        }
    }
