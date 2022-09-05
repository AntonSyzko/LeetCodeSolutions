package leet.code.solutions.arrays;

import java.util.List;

public class PlusOne {

    public static void main(String[] args) {
        List<Integer> original = List.of(1, 2, 9);
        List<Integer> withAddedOne = plusOne(original);
        System.out.println(withAddedOne);
    }

    private static List<Integer> plusOne(List<Integer> nums) {
        int sizeOfNums = nums.size() - 1;
        nums.set(sizeOfNums, nums.get(sizeOfNums) + 1);

        for (int i = sizeOfNums; i > 0 && nums.get(i) == 10; --i) {
            nums.set(i, 0);
            nums.set(i - 1, nums.get(i - 1) + 1);
        }

        if (nums.get(0) == 10) {
            nums.set(0, 0);
            nums.add(0, 1);
        }

        return nums;
    }
}
