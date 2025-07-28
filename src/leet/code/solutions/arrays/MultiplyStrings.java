package leet.code.solutions.arrays;

public class MultiplyStrings {

    public static void main(String[] args) {
        String s1 = "12";
        String s2 = "16";

        String s3 = multiply(s1, s2);
        System.out.println(s3);
    }


    private static String multiply(String num1, String num2) {
        int num1Len = num1.length();
        int num2Len = num2.length();

        // Result can be at most num1Len + num2Len digits
        int[] positions = new int[ num1Len + num2Len];

        // Multiply each digit of num2 with each digit of num1
        for(int i = num1Len - 1; i >= 0; i--) {//iter backwards
          for(int j = num2Len - 1 ; j >= 0; j--) {
              // Get the digits
              int digit1 = num1.charAt(i) - '0';
              int digit2 = num2.charAt(j) - '0';

              // Multiply the digits
              int multiply = digit1 * digit2;
              int sum = multiply + positions[ i  + j + 1];//positions[ i  + j + 1] holds tenth carry from previous iter

              positions[i + j] += sum / 10;// Add to existing carry, don't overwrite
              positions[i + j + 1] = sum % 10;//stores tenth carry

          }
      }

        StringBuilder sb = new StringBuilder();
      boolean isLeadingZero = true;


        for (int position : positions) {
            if (position != 0) {
                isLeadingZero = false;
            }

            if (!isLeadingZero) {
                sb.append(position);
            }
        }

      return sb.isEmpty() ? "0" : sb.toString();

    }
 }
