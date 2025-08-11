package leet.code.solutions.two_pass;

import java.util.Arrays;

public class Candy {

    public static void main(String[] args) {

    }

    private static int candy(int[] ratings) {
        final int len = ratings.length;

        int ans = 0;

        int[] leftPass = new int[len];
        int[] rightPass = new int[len];

        Arrays.fill(leftPass, 1);
        Arrays.fill(rightPass, 1);

        for (int i = 1; i < len; ++i) {//iterate from 1 -------------->
            if (ratings[i] > ratings[i - 1]) {//if current is bigger than PREV, i.e. at LEFT
                leftPass[i] = leftPass[i - 1] + 1;//prev LEFT + 1
            }
        }

        for (int i = len - 2; i >= 0; --i) {//iterate backwards <------------
            if (ratings[i] > ratings[i + 1]) {//if curr > prev from end , i.e. at right
                rightPass[i] = rightPass[i + 1] + 1;//prev RIGHT + 1
            }
        }

        for (int i = 0; i < len; ++i) {
            ans += Math.max(leftPass[i], rightPass[i]);//max of both
        }

        return ans;
    }
}