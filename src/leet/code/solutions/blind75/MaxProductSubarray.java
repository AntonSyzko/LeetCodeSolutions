package leet.code.solutions.blind75;

public class MaxProductSubarray {

    public static void main(String[] args) {
      int[] nums = {3,-1,0,4};
      int maxProd = maxProduct(nums);
      System.out.println(maxProd);

        int[] nums2 = {-2,3,0,-4};
        int maxProduct = maxProduct(nums2);
        System.out.println(maxProduct);
    }


    /*
            Time complexity:
                 O(n)
            Space complexity:
                 O(1)
     */
    private static int maxProduct(int[] nums) {
        int resMAx = nums[0];
        int currMax = 1;
        int currMIn = 1;

        for (int num : nums) {
            int tempMax = currMax * num;//just not to lose in line 32 where currMax is used but got updated in line 31
            currMax = Math.max(Math.max( currMax * num, currMIn * num), num);
            currMIn = Math.min(Math.min( tempMax , currMIn * num), num);

            resMAx = Math.max(resMAx, currMax);
        }

        return resMAx;
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
