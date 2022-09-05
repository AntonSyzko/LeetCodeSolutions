package leet.code.solutions.arrays;

/*
aabbc = 2a2bc
2a3b2c = aabbbcc
 */
public class RunLengthEncoding {
    public static void main(String[] args) {
        String toEncode = "aabbc";
        String encoded = encode(toEncode);
        System.out.println(encoded);

        String decoded = decode(encoded);
        System.out.println(decoded);
    }

    private static String encode(String input) {
        StringBuilder sb = new StringBuilder();
        int count = 1;//1  is default for one char met

        for (int i = 1; i <= input.length(); i++) {
            //END char OR  Found new character so write the count of previous character. NOT SAME
            if (i == input.length() || input.charAt(i) != input.charAt(i - 1)) {

                sb.append(count);
                sb.append(input.charAt(i - 1));//since NEW different from prev found - so count is for PREV
                count = 1;//reset - 1 is default

            } else {// s.charAt(i) == s.charAt(i - 1). consecutive chars are SAME

                ++count;
            }
        }

        return sb.toString();
    }

    private static String decode(String input) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {

            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar)) {

                count = count * 10 + currentChar - '0';//*10 to accounbt for 12,25, 160 etc - to aggregate TENs

            } else {//char is a letter of alphabet.

                while (count > 0) {// Appends count times  copies of char to result.
                    sb.append(currentChar);
                    count--;
                }
            }
        }
        return sb.toString();
    }
}
