package leet.code.solutions.arrays;

/*
[1,2,3,1]

res = index = 2 as peak element is number - 3


 */
public class FindPeakElement {

    public static void main(String[] args) {
     int[] nums = {1,2,3,1};
     int peakElement = findPeakElement(nums);
     System.out.println(peakElement);

       int[]nums2 = {1,2,3,4};//all increasing
         peakElement = findPeakElement(nums2);
        System.out.println(peakElement);

        int[] nums3 = {4,3,2,1};//alldecreasing
         peakElement = findPeakElement(nums3);
        System.out.println(peakElement);

        int[] nums4 = {1,3,2,1,4,3};//any first peal is allowed
        peakElement = findPeakElement(nums4);
        System.out.println(peakElement);
    }

    // time O(log N)
    //space O(1)
    private static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[mid + 1]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
