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

        do {
            res.append((char) ('0' + Math.abs(num % 10)));
            num = num / 10;
        } while (num != 0);

        if (isNegative) res.append('-');

        return res.reverse().toString();
    }

    private static int stringToInt(String numStr) {
        int res = 0;

        for (int i = numStr.charAt(0) == '-' ? 1 : 0; i < numStr.length(); i++) {
            final int digit = numStr.charAt(i) - '0';
            res = res * 10 + digit;
        }

        return numStr.charAt(0) == '-' ? -res : res;
    }

}

