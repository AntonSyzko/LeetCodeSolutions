package leet.code.solutions.arrays;

import java.util.Arrays;

public class ClosestNonNegative {

    public static void main(String[] args) {
        int num = 120;
        int closes = closestNonNegative(num);
        System.out.println(closes);
    }

    private static int closestNonNegative(int num){
        if(num < 0 ){
            return 0;
        }

        if(num < 10){
            return num + 1;
        }

        char[] chars = String.valueOf(num).toCharArray();
        Arrays.sort(chars);

        StringBuilder sb = new StringBuilder(new String(chars));

        return Integer.parseInt(sb.reverse().toString());
    }
}