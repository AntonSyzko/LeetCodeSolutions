package leet.code.solutions.arrays;

public class AddStrings {

    public static void main(String[] args) {
        String num1 = "456";
        String num2 = "77";
        System.out.println(addStrings(num1, num2));
    }

    private static String addStrings(String num1, String num2) {
        int nums1Counter = num1.length() - 1;
        int nums2Counter = num2.length() - 1;

        StringBuilder result = new StringBuilder();
        int carry = 0;

        while (nums1Counter >= 0 || nums2Counter >= 0 || carry > 0) {
            int digit1 = nums1Counter >= 0 ? num1.charAt(nums1Counter) - '0' : 0;
            int digit2 = nums2Counter >= 0 ? num2.charAt(nums2Counter) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);

            nums1Counter--;
            nums2Counter--;
        }

        // The result is built in reverse order, so we need to reverse it
        return result.reverse().toString();
    }
}
