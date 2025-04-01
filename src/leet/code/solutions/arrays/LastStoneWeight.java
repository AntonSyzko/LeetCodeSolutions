package leet.code.solutions.arrays;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/last-stone-weight/description/

You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.



Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1

Explanation:
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.

Example 2:

Input: stones = [1]
Output: 1


Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 1000
 */
public class LastStoneWeight {

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};//1
        int lastStoneLeft = lastStoneWeight(stones);
        System.out.println(lastStoneLeft);

        int[] stones2 = {1};//1
         lastStoneLeft = lastStoneWeight(stones2);
        System.out.println(lastStoneLeft);

        int[] stones3 = {1,3};//2
          lastStoneLeft = lastStoneWeight(stones3);
        System.out.println(lastStoneLeft);

        int[] stones4 = {3, 7, 2};//2
           lastStoneLeft = lastStoneWeight(stones4);
        System.out.println(lastStoneLeft);

        int[] stones5 = {2,2};//0
          lastStoneLeft = lastStoneWeight(stones5);
        System.out.println(lastStoneLeft);
    }


    // time O ( n * log N )
    // space O ( N)
    private static int lastStoneWeight(int[] stones) {
        if(stones==null || stones.length==0){
            return 0;
        }

        if(stones.length==1){
            return stones[0];
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);

        for (int each : stones) {
            maxHeap.add(each);
        }

        while (maxHeap.size() > 1) {
            int firstStone = maxHeap.remove();
            int secondStone = maxHeap.remove();

             if(firstStone != secondStone){
                 maxHeap.add(firstStone - secondStone);
             }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.remove();
    }
}
