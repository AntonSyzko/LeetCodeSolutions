package leet.code.solutions.greedy;


import java.util.Arrays;

/*
https://leetcode.com/problems/bag-of-tokens/description/

You start with an initial power of power, an initial score of 0, and a bag of tokens given as an integer array tokens, where each tokens[i] denotes the value of tokeni.

Your goal is to maximize the total score by strategically playing these tokens. In one move, you can play an unplayed token in one of the two ways (but not both for the same token):

Face-up: If your current power is at least tokens[i], you may play tokeni, losing tokens[i] power and gaining 1 score.
Face-down: If your current score is at least 1, you may play tokeni, gaining tokens[i] power and losing 1 score.
Return the maximum possible score you can achieve after playing any number of tokens.

Example 1:

Input: tokens = [100], power = 50

Output: 0

Explanation: Since your score is 0 initially, you cannot play the token face-down. You also cannot play it face-up since your power (50) is less than tokens[0] (100).

Example 2:

Input: tokens = [200,100], power = 150

Output: 1

Explanation: Play token1 (100) face-up, reducing your power to 50 and increasing your score to 1.

There is no need to play token0, since you cannot play it face-up to add to your score. The maximum score achievable is 1.

Example 3:

Input: tokens = [100,200,300,400], power = 200

Output: 2

Explanation: Play the tokens in this order to get a score of 2:

Play token0 (100) face-up, reducing power to 100 and increasing score to 1.
Play token3 (400) face-down, increasing power to 500 and reducing score to 0.
Play token1 (200) face-up, reducing power to 300 and increasing score to 1.
Play token2 (300) face-up, reducing power to 0 and increasing score to 2.
The maximum score achievable is 2.
 */
public class BagOfTokens {

    public static void main(String[] args) {
        int[] tokens = {100,200,300,400};
        int power = 200;
        int finalScore =  bagOfTokensScore(tokens, power);
        System.out.println(finalScore);

        int[] tokens2 = {200,100};
        int power2 = 150;
        int finalScore2 =  bagOfTokensScore(tokens2, power2);
        System.out.println(finalScore2);

        int[] tokens3 = {100};
        int power3 = 50;
        int finalScore3 =  bagOfTokensScore(tokens3, power3);
        System.out.println(finalScore3);
    }

    /*
    Time and Space Complexity:

        Time Complexity: O(n log n) - dominated by the sorting operation
        Space Complexity: O(1) - using constant extra space for variables
     */
    private static int bagOfTokensScore(int[] tokens, int power) {
        if(tokens.length == 0) return 0;

        Arrays.sort(tokens);

        if(tokens[0] > power) return 0;//if to start with already too high

        int score = 0;
        int maxScore = 0;

        //two pointers
        int left = 0;
        int right = tokens.length - 1;

        while(left <= right) {

            int lowestToken = tokens[left];
            int highestToken = tokens[right];

            if(lowestToken <= power){//power is enough to gain SCORE

                power -= lowestToken;//give away power

                score++;//gain score
                maxScore = Math.max(maxScore, score);

                left++;//drop(move) lowest

            }else if(score > 0 &&  left < right){// have SCORE and still room to gain in future ( otherwise no point to give AWAY SCORE

                power += highestToken;//gain power
                score--;//give away score

                right--;//drop (move) highest

            }else{//no score to give, no power to gain score
                break;
            }
        }

        return maxScore;
    }
}
