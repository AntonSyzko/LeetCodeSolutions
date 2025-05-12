package leet.code.solutions.priority_queue;

import java.util.PriorityQueue;

/*

given array of stick length , you may connect two sticks in 1 by combining length of two sticks
this combination is a cost of aggreagtion

combine sticks until 1 long single stick is left

combine sticks in one with minimal cost possible

return minimum cost it takes to aggregate all stick into one gigantesca

 */
public class MinCostToConnectSticks {

    public static void main(String[] args) {
        int[] sticks = {1,8,5,3};
        int minCost = connectSticks(sticks);
        System.out.println(minCost);

        int[] sticks2 = {2,4,3};
        int minCost2 = connectSticks(sticks2);
        System.out.println(minCost2);
    }

    /*
    Complexity Analysis:

        Time Complexity: O(n log n) where n is the number of sticks

        Building heap: O(n)
        n-1 iterations, each with O(log n) operations

        Space Complexity: O(n) for the heap
     */
    private static int connectSticks(int[] sticks){

        if(sticks==null || sticks.length==0) return 0;
        if(sticks.length==1) return 0;
        if(sticks.length==2) return (sticks[0] + sticks[1]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b)-> a- b);
        for(int stick : sticks){
            minHeap.add(stick);
        }

        int aggregationCost = 0;

        while(minHeap.size() >1){//if two or more sticks are available still

            int firstSmallest = minHeap.poll();
            int secondSmallest = minHeap.poll();

            int combinedNewStick = firstSmallest + secondSmallest;//combining stick in obe is a cost

            aggregationCost += combinedNewStick;//aggregate cost as we combine

            minHeap.offer(combinedNewStick);//put back in min hip so that new combined stick could be aggreagted with other sticks in hip
        }

        return aggregationCost;
    }
}
