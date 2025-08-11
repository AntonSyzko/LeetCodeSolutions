package leet.code.solutions.priority_queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class CLosestNonNegative {

    public static void main(String[] args) {
        int num = 120;
        int closes = closestNonNegativeHeap(num);
        System.out.println(closes);
    }

    private static int closestNonNegativeHeap(int num){
        if(num < 0){
            return 0;
        }

        if(num < 10){
            return num + 1;
        }

        Queue<String> maxHip = new PriorityQueue<>((a, b) -> (b + a).compareTo(a + b));

        String numAsString = String.valueOf(num);

        for(char ch : numAsString.toCharArray()){
            maxHip.offer(String.valueOf(ch));
        }

        StringBuilder sb = new StringBuilder();
        while(!maxHip.isEmpty()){
            sb.append(maxHip.poll());
        }

        return Integer.parseInt(sb.toString());
    }
}