package leet.code.solutions.strings;

public class IntToStringAndBack {

    public static void main(String[] args) {
        int num = -123;
        String numAsString = intToString(num);
        System.out.println(numAsString);

        int back = stringToInt(numAsString);
        System.out.println(back);
    }

    private static String intToString(int num) {
        boolean isNegative = num < 0;
        StringBuilder res = new StringBuilder();

        while (num  != 0) {

            res.append((char) ('0' + Math.abs(num % 10)));

            num = num / 10;

        }

        if (isNegative) res.append('-');

        return res.reverse().toString();
    }

    private static int stringToInt(String numStr) {
        int res = 0;

        boolean negative = numStr.charAt(0) == '-';
        int startingIndex = negative ? 1 : 0;

        for (int i = startingIndex; i < numStr.length(); i++) {
            final int digit = numStr.charAt(i) - '0';
            res = (res * 10) + digit;
        }

        return negative ? -res : res;
    }

}

