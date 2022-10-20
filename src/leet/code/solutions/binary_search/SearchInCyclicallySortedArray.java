package leet.code.solutions.binary_search;

import java.util.List;

public class SearchInCyclicallySortedArray {

    public static void main(String[] args) {

    }

    public static int searchSmallest(List<Integer> nums) {
        int left = 0, right = nums.size() - 1;

        while (left < right) {

            int mid = left + ((right - left) / 2);

            if (nums.get(mid) > nums.get(right)) {
                //Minimum must be in nums.subList(mid+1,right+1).
                left = mid + 1;

            } else { //nums.get(mid) < nums.get(right).
            // Minimum cannot be in nums.subList(mid + 1, right + 1) so it must be in nums.sublist(left, mid + 1).
                right = mid;

            }
        }
        // Loop ends when left == right .
        return left;
    }
}




