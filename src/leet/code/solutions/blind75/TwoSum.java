package leet.code.solutions.blind75;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
          int[] nums = {2, 11, 15,7};
          int target = 9;
          int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    //O(N)
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int diffToTarget = target - nums[i];

            if(map.containsKey(diffToTarget)){
                return new int[]{map.get(diffToTarget), i};
            }
            map.put(nums[i], i);
        }

        return null;
    }
}
