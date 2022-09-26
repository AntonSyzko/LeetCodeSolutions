package leet.code.solutions.binary_search;

import java.util.List;

public class EntryEqualsIndex {

    public static void main(String[] args) {
     List<Integer> nums = List.of(-1,0,2,3,7,8);
     int indexAndValueSame = searchEntryEqualToItsIndex(nums);
        System.out.println(indexAndValueSame);
    }

    private static int searchEntryEqualToItsIndex(List<Integer> nums) {
        int left = 0, right = nums.size();

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int differenceBetweenValueAndIndex = nums.get(mid) - mid;

            //A.get(mid)==mid  if and only if difference == 0.
            if (differenceBetweenValueAndIndex == 0) {
                return mid;
            } else if (differenceBetweenValueAndIndex > 0) {
                right = mid - 1;
            } else { //difference < 0
                left = mid + 1;
            }
        }
        return -1;
    }

}
