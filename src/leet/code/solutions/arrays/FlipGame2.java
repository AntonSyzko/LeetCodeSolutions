package leet.code.solutions.arrays;

/*

given board of ++
each player changes pair of ++ to pair of --
check if first player can win

example : ++++
player changes middle ++ to '--' = ++--++
whatever next does - first will win
 */
public class FlipGame2 {

    public static void main(String[] args) {
        String board = "++++";
        System.out.println(cahWin(board));
    }

    private static boolean cahWin(String board){
        if(board == null || board.length() < 2){//BASE
            return false;
        }

        for (int i = 0; i < board.length()-1; i++) {

            if(board.charAt(i) == '+' && board.charAt(i + 1) == '+'){

                String nextBoardState = board.substring(0, i) + "--" + board.substring(i + 2);

                if(!cahWin(nextBoardState)){//this is next attempt after first, third etc ... so next player's attempt

                    return true;//if other player cannot win - I WON !!!!

                }
            }
        }

        return false;
    }
}
