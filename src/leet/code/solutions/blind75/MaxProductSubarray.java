package leet.code.solutions.blind75;

public class MaxProductSubarray {

    public static void main(String[] args) {
      int[] nums = {3,-1,4};
      int maxProd = maxProductSubarray(nums);
      System.out.println(maxProd);
    }

    private static int maxProductSubarray(int[] nums) {
        if(nums.length==0) return 0;

        int min = nums[0];
        int max = nums[0];
        int res = max;

        for(int i = 1; i < nums.length;i++){

            int curr = nums[i];

            //for MAX we will chose  max of 3: curr itself , product of curr AND max, product of curr AND min

            //if we use max directly we will override it and overridden obe will be used for MIn calc, which is wrong -> hence temp
            int tempMax = Math.max(curr, Math.max((max * curr), (min * curr)));

            //for MIN we will chose  MIN of 3: curr, product of curr AND previous ( not temp) max, product of curr AND min
            min = Math.min(min, Math.min((min * curr), (max * curr)));

            //update max to previously calculated
            max = tempMax;

            res = Math.max(res, max);
        }

        return res;
    }
}
