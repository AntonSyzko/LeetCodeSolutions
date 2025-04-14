package leet.code.solutions.arrays;


public class Kadanes {

    public static void main(String[] args) {

         int[] nums = {4,-1,2,-7,3,4,-8,56};
         int largestSUm =  largestSum(nums);
         System.out.println(largestSUm);

        int[] nums2 = {-2,-1,-3};
         int largestSUm2 =  largestSum(nums2);
        System.out.println(largestSUm2);
    }

    private  static int largestSum( int[] nums) {
      int largestSUm = nums[0];
      int ongoingSum = 0;

        for (int i = 0; i < nums.length; i++) {

            if(ongoingSum <0){//if sum is negative - it won't contribute to GROW forther
                ongoingSum = 0;//reset to zero
            }

            ongoingSum += nums[i];

            largestSUm = Math.max(ongoingSum,largestSUm);
        }

        return largestSUm;
    }
}
