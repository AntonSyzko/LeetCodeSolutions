package leet.code.solutions.priority_queue;

import java.util.*;

/*
Star has  coordinates , Earth is 0,0,0
Find closes starts to Earth
 */
public class ClosestStarts {

    public static void main(String[] args) {
        List<Star> list = List.of(new Star(2,2,2), new Star(3,3,3), new Star(5,5,5),new Star(9,9,9));
        List<Star> closesStars = findClosestKStars(2, list.listIterator());
        System.out.println(closesStars);
    }


    public static List<Star> findClosestKStars(int k, Iterator<Star> stars){
        // maxHeap to store the closest k stars seen so far.
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        while (stars.hasNext()) {
            // Add each star to the max-heap. If the max-heap size exceeds k, remove  the maximum element from the max-heap.
            Star nextStar = stars.next();
            maxHeap.add(nextStar);

            if(maxHeap.size() > k){
                maxHeap.remove();
            }
        }

        List<Star> closestStarts = new ArrayList<>(maxHeap);
        // The only guarantee PriorityQueue makes about ordering is that the  maximum element comes first, so we sort orderedStars.
        Collections.sort(closestStarts);

        return closestStarts;
    }

    static class Star implements Comparable<Star> {
        private final double x;
        private final double y;
        private final double z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distance() {
            return Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Star other) {
            return Double.compare(this.distance(), other.distance());
        }

        @Override
        public String toString() {
            return "Star{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
        }
    }

}






