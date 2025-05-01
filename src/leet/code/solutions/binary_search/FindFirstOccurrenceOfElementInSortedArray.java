package leet.code.solutions.binary_search;

import java.util.List;

public class FindFirstOccurrenceOfElementInSortedArray {

    public static void main(String[] args) {
         List<Integer> nums = List.of(1,2,3,3,3,4,5);
         int firstOccurrence = searchFirstOfK(nums, 3);
            System.out.println(firstOccurrence);
    }

    public static int searchFirstOfK(List<Integer> nums, int target) {
                int left = 0;
                int right = nums.size() - 1;
                int result = -1;

        while (left <= right) {

            int mid = left + ((right - left) / 2);

           if (nums.get(mid) == target) {

                result = mid;
                // Nothing to the right of mid can be the first occurrence of k.
                right = mid - 1;

            }  else if (nums.get(mid) > target) {

                right = mid - 1;//search below current mid

            } else {// A.get(mid) < k

                left = mid + 1;//search above current mid 
            }
        }
        return result;
    }
}
