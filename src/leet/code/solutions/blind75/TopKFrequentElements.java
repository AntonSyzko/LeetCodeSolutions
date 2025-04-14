package leet.code.solutions.blind75;

import java.util.*;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/top-k-frequent-elements/


Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
public class TopKFrequentElements {

    public static void main(String[] args) {
      int[] nums = {1,1,1,2,2,3};
      int k = 2;
      int[] res =  topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }


    /*

    Time

         O(n * log K )

    Space Complexity

        HashMap: O(m) for m unique elements
        PriorityQueue: O(m) for m unique elements
        Result array: O(k)

Overall space complexity: O(m)
     */
    private static int[] topKFrequent(int[] nums, int k) {


        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for(int each : nums){
            frequencyMap.put(each, frequencyMap.getOrDefault(each, 0) + 1);
        }

        //min heap will keep SMALLEST elements on top
        Queue<Integer> minHeap = new PriorityQueue<>((a,b) -> frequencyMap.get(a)-frequencyMap.get(b));

        for(int each : frequencyMap.keySet()){
            minHeap.offer(each);

            //dropping is limited to K, thus polling from q is (log K)
            if(minHeap.size() > k ){// so if heap size is BIGGER than K  -> we will drop elements
                minHeap.poll();//smallest will be dropped
            }
        }

        //so now BIGGEST left

        return minHeap.stream().mapToInt(i->i).toArray();
    }

    // O (N log N ) -- less efficient than min heap limited to K polling
    private static int[] topKFrequentMaxHeap(int[] nums, int k) {

        int[] res = new int[k];

     Map<Integer, Integer> frequencyMap = new HashMap<>();

     for(int each : nums){
         frequencyMap.put(each, frequencyMap.getOrDefault(each, 0) + 1);
     }

     Queue<Integer> maxHeap = new PriorityQueue<>((a,b) -> frequencyMap.get(b)-frequencyMap.get(a));
     maxHeap.addAll(frequencyMap.keySet());


         while(k - 1 >= 0){
             res[k-1] = maxHeap.poll();
             k--;
         }


     return res;
    }



    private static int[] topKFrequentUsingSortingMap(int[] nums, int k) {

        int[] res = new int[k];

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for(int each : nums){
            frequencyMap.put(each, frequencyMap.getOrDefault(each, 0) + 1);
        }

        // Assuming frequencyMap is a Map<Integer, Something>
       return frequencyMap.keySet().stream().sorted().limit(2).mapToInt(Integer::intValue).toArray();  // Get primitive int array

    }
}
