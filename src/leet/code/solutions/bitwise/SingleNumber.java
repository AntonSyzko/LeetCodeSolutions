package leet.code.solutions.bitwise;

public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = { 1,2,3,3};
        int res = singleNUmber(nums);
        System.out.println(res);
    }

    private static int singleNUmber(int[] nums){
        int ans = 0;

        for (int each : nums) {
            ans  ^= each;
        }

        return ans;
    }
}