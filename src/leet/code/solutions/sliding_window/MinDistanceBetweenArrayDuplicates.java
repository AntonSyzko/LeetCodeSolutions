package leet.code.solutions.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
We define the distance between two array values as the number of indices between the two values.
 Given array with duplicates  , find the minimum distance between any pair of equal elements in the array.
  If no such value exists, print -1

  7 1 3 4 1 7
  7 to t = 5 - 0 = 5
  1 to 1 = 4 -1 = 3
  min(5,3) = 3
 */
public class MinDistanceBetweenArrayDuplicates {


    public static void main(String[] args) {
        int[] nums = {7, 1, 3, 4, 1, 7};
        int min = minDistance(nums);
        System.out.println(min);

        int[] nums2 = {7, 1, 3, 4, 11, 71};
        int min2 = minDistance(nums2);
        System.out.println(min2);
    }



    // o(n)
    private static int minDistance(int [] nums){
        Map<Integer, Integer> map = new HashMap<>();// number vs it's index

        int minDistSoFarRes = Integer.MAX_VALUE;
        int prevIndex = 0;
        int currentIndex = 0;

        for (int i = 0; i < nums.length; i++) {

            if( map.containsKey(nums[i])){//is already in MAP - so - duplicate

                currentIndex = i;//currently here
                prevIndex = map.get(nums[i]);//stored before index

                minDistSoFarRes  = Math.min(minDistSoFarRes, (currentIndex - prevIndex));//take MIN of curr and dist

            }

            map.put(nums[i], i);//store in MAP anyway always

        }
        return minDistSoFarRes == Integer.MAX_VALUE ? -1 : minDistSoFarRes;

    }
}