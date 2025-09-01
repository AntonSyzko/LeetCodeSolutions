package leet.code.solutions.two_pointers;

import java.util.Arrays;

/*
The problem provides us with an array of integers nums and another integer target.
Our task is to count the number of unique triplets (i, j, k), where i, j, and k are the indices in the array such that 0 <= i < j < k < n,
 and the sum of the elements at these indices is less than the given target.
 More formally, we want to find the count of combinations where nums[i] + nums[j] + nums[k] < target.

[3,1,0,2], target 5

triplets  [0,1,2] < 5
          [0,1,3] < 5
 */
public class ThreeSumSmaller {

    public static void main(String[] args) {
        int[] n = {3,1,0,2};
        int smaller = threeSumSmaller(n,5);
        System.out.println(smaller);
    }

    /*
    Time
    sorting is O(n log n)  but is overshadowed by O(n^2), the overall time complexity of the code is O(n^2).
    space O(1)
     */
    private static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);

        int count = 0;

        for (int first = 0; first < nums.length - 2; first++) {

            int second = first + 1;
            int last = nums.length - 1;

            while (second < last) {

                int sum = nums[first] + nums[second] + nums[last];

                if(sum >= target){//sum too high

                    last--;

                }else{

                    count += last - second; // all indices between last and second included in result

                    second++;
                }
            }

        }
        return count;
    }
}