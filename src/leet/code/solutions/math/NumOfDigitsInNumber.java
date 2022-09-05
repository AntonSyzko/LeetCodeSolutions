package leet.code.solutions.math;

public class NumOfDigitsInNumber {

    public static void main(String[] args) {
        int num = 523;
        int digitsNum = findNumberOfDigitsInNumber(num);
        System.out.println(digitsNum);

        int mostSignificantDigit = findMostSignificantDigit(num, digitsNum);
        System.out.println(mostSignificantDigit);
    }

    private static int findNumberOfDigitsInNumber(int num) {
        if (num == 0) return 1;//int overflow for 0

//floor double to lower int      log10 -to get digits       +1 to account for base ?
        return (int) Math.floor(Math.log10(Math.abs(num))) + 1;
    }

    private static int findMostSignificantDigit(int num, int numOfDigitsInNum) {

        int mostSignificantMask = (int) Math.pow(10, numOfDigitsInNum - 1);

        return num / mostSignificantMask;
    }
}
