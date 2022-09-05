package leet.code.solutions.arrays;

import java.util.Arrays;

/*
smallest non contructible is previously constructed +1
12,2,1,15,2,4  -> sorted 1,2,2,4,12,15
1 - smallest non const is 2 ( 1+1)
2 - smallest 2+1 = 3
2 - 2 +3 = 5
4 - 5+4 = 9
12 != 9 +1 ( previous +1 ) - so smallest constructible value of previous values is 10
 */

public class SmallestNonConstructibleValue {

    public static void main(String[] args) {
        //int[] nums = {12, 2, 1, 15, 2, 4};
        int[] nums = {2,3,1};
        int res = smallestNonConstructibleValue(nums);
        System.out.println(res);
    }

    private static int smallestNonConstructibleValue(int[] nums) {
        Arrays.sort(nums);
        int maxConstructibleValue = 0;

        for (int num : nums) {
            if (num > maxConstructibleValue + 1) {//if current NUM is more than previously ALL aggregated +1
                break;
            }
            maxConstructibleValue += num;//just aggreagtes SUM of all ongoing NUMS  untill break hits
        }
        return maxConstructibleValue + 1;//all agregated +1 ( plus ONE is to indicate NON constructible - the one we did not reach but smallest to one we have aggregated )
    }
}
