package leet.code.solutions.blind75;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};

        int[] products = productExceptSelf(nums);

        System.out.println(Arrays.toString(products));
    }

    private static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        Arrays.fill(result, 1);
        int prefix = 1;
        int postfix = 1;

        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix = prefix * nums[i] ;
        }

        for (int i = nums.length - 1; i >= 0; i--) {//reverse iteration
            result[i] = result[i] * postfix;
            postfix = postfix * nums[i] ;
        }

        return result;
    }


        //O(n)
    private static int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            int leftProduct = 1;// * times 1 never changes product - so it is safe to start with
            int rightProduct = 1;

            //produc of nums to the LEFT of current
            for(int left = 0; left < i ; left++){
                    leftProduct *= nums[left];
            }

           //product of nums to the RIGHT of current
            for(int right = i + 1; right < nums.length ; right++){
                    rightProduct *= nums[right];
            }

            result[i] = leftProduct * rightProduct;

        }

        return result;
    }
}
