package leet.code.solutions.arrays;

import java.util.List;

public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        List<Integer> numsWithDuplicates = List.of(1, 1, 1, 2, 3);
        int duplicatesFound = deleteDuplicates(numsWithDuplicates);
        System.out.println(duplicatesFound);
    }

    private static int deleteDuplicates(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }

        int lastUniqueIndexToWriteTo = 1;//zero index is default UNIQUE, we  dont event touch it

        for (int i = 1; i < nums.size(); i++) {
            if (!nums.get(lastUniqueIndexToWriteTo - 1).equals(nums.get(i))) {// so NON duplicate - UNIQUE
                nums.set(lastUniqueIndexToWriteTo++, nums.get(i));//update res of uniques and mind ++
            }
        }
        //returns No of valid entries after deletion
        return lastUniqueIndexToWriteTo;
    }

}
