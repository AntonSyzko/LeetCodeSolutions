package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.List;

/*
In the given problem, we are playing a game called Flip Game with a friend. The game is played with a string currentState that can consist of only two characters: '+' and '-'.
In each turn, a player must flip exactly two consecutive '+' characters into '-' characters. This is the only valid move in the game.
 The game proceeds with each player taking turns until no more valid moves can be made, at which point the last player to have made a valid move wins.

Our objective is to find all possible states of the currentState after one valid move has been made by the current player. We must provide a list of these possible states, or an empty list if no valid moves are available.
The resulting states can be returned in any order.


 */
public class FlipGame {

    public static void main(String[] args) {
        String s = "++++";
        List<String> list = generatePossibleNextMoves(s);
        System.out.println(list);

        s = "++--";
        list = generatePossibleNextMoves(s);
        System.out.println(list);

        s = "+-";
        list = generatePossibleNextMoves(s);
        System.out.println(list);

        s = "++--++-";
        list = generatePossibleNextMoves(s);
        System.out.println(list);

        s = "--++--+";
        list = generatePossibleNextMoves(s);
        System.out.println(list);
    }

    private static List<String> generatePossibleNextMoves(String currentState) {
        List<String> possibleNextMoves = new ArrayList<>();
        char[] chars = currentState.toCharArray();

        for (int i = 0; i < chars.length -1 ; i++) {
            // Check if the current and next character are both '+'
            if(chars[i] == '+' && chars[i+1] == '+'){
                //set em both to '-'
                chars[i] = '-';
                chars[i+1] = '-';

                // Add the new state to the list of possible moves. Convert the character array back to a string.
                possibleNextMoves.add(String.valueOf(chars));

                // Reset the characters back to '+' for the next iteration
                chars[i] = '+';
                chars[i+1] = '+';
            }
        }
        return possibleNextMoves;
    }
}
