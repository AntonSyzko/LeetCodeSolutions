package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
rearrane so that
nums[0] <= nums[1] >= nums[2] <= nums[3] >= nums[4] .....etc
 */
public class RearrangeArray {

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        List<Integer> rearranged = rearrange(nums);
        System.out.println(rearranged);
    }

    private static List<Integer> rearrange(List<Integer> nums) {
        List<Integer> copy = new ArrayList<>(nums);//needed for swap
        for (int i = 1; i < copy.size(); i++) {

            if ((i % 2 == 0) && (copy.get(i - 1) <= copy.get(i)) // even number and <= next to it
                || // OR !!!
                (i % 2 != 0) && (copy.get(i - 1) >= copy.get(i))) { //odd number and >= next to it
                Collections.swap(copy, i - 1, i); //swap em
            }
        }
        return copy;
    }
}
