package leet.code.solutions.priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://neetcode.io/problems/last-stone-weight

You are given an array of integers stones where stones[i] represents the weight of the ith stone.

We want to run a simulation on the stones as follows:

At each step we choose the two heaviest stones, with weight x and y and smash them togethers
If x == y, both stones are destroyed
If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
Continue the simulation until there is no more than one stone remaining.

Return the weight of the last remaining stone or return 0 if none remain.

Example 1:

Input: stones = [2,3,6,2,4]

Output: 1
Explanation:
We smash 6 and 4 and are left with a 2, so the array becomes [2,3,2,2].
We smash 3 and 2 and are left with a 1, so the array becomes [1,2,2].
We smash 2 and 2, so the array becomes [1].

Example 2:

Input: stones = [1,2]

Output: 1
Constraints:

1 <= stones.length <= 20
1 <= stones[i] <= 100
 */
public class LastStoneWeight {

    public static void main(String[] args) {
        int[] stones = {2,3,6,2,4};
        int lastStoneWeight = lastStoneWeight(stones);
        System.out.println(lastStoneWeight);

        int[] stones2 = {1,2};
        int lastStoneWeight2 = lastStoneWeight(stones2);
        System.out.println(lastStoneWeight2);

        int[] stones3 = {3,3};
        int lastStoneWeight3 = lastStoneWeight(stones3);
        System.out.println(lastStoneWeight3);
    }


    private static int lastStoneWeight(int[] stones) {
        Queue<Integer> maxHip = new PriorityQueue<>((a,b) -> b -a);
        maxHip.addAll(Arrays.stream(stones).boxed().toList());

        while (maxHip.size() > 1) {
            int heaviestStone1 = maxHip.remove();
            int heaviestStone2 = maxHip.remove();

            if(heaviestStone1 == heaviestStone2){
                continue;
            }

            int smashedStonesResult = heaviestStone1 > heaviestStone2 ? heaviestStone1 - heaviestStone2 : heaviestStone2 - heaviestStone1;
            maxHip.add(smashedStonesResult);
        }

        return maxHip.isEmpty() ? 0 : maxHip.peek();
    }
}
