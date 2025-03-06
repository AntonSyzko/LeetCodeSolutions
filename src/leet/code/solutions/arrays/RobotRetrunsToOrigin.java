package leet.code.solutions.arrays;

/*
'U, D" -> Up Down = true

"L, L " -> left , left - false
 */
public class RobotRetrunsToOrigin {

    public static void main(String[] args) {
        String moves = "LLUUDDRR";
        boolean isRetrunToOrigin = isReturnToOrigin(moves);
        System.out.println(isRetrunToOrigin);

        moves = "LL";
         isRetrunToOrigin = isReturnToOrigin(moves);
        System.out.println(isRetrunToOrigin);

        moves = "LLR";
        isRetrunToOrigin = isReturnToOrigin(moves);
        System.out.println(isRetrunToOrigin);

        moves = "LLRRD";
        isRetrunToOrigin = isReturnToOrigin(moves);
        System.out.println(isRetrunToOrigin);
    }

    private static boolean isReturnToOrigin(String moves) {
        int upDownMoves = 0;
        int leftRightMoves = 0;

        for (int i = 0; i < moves.length(); i++) {
            char currentMove = moves.charAt(i);
            if(currentMove == 'U'){
                upDownMoves++;
            } else if(currentMove == 'D'){
                upDownMoves--;
            }else if(currentMove == 'L'){
                leftRightMoves++;
            } else if (currentMove == 'R') {
                leftRightMoves--;
            }
        }

        return upDownMoves == 0 && leftRightMoves == 0;

    }


    private static boolean isReturnToOriginMy(String moves) {
        if(moves == null || moves.length() == 0){
            return false;
        }

        if(moves.length() % 2 != 0){
            return false;
        }

        boolean isReturnToOrigin = false;

        int start = 0;
        int end = moves.length() - 1;

        while (start < end) {
            if((moves.charAt(start) == 'U' && moves.charAt(end) == 'D') || (moves.charAt(start) == 'D' && moves.charAt(end) == 'U')) {
                isReturnToOrigin = true;
            } else if ((moves.charAt(start) == 'L' && moves.charAt(end) == 'R') || (moves.charAt(start) == 'R' && moves.charAt(end) == 'L')) {
                isReturnToOrigin = true;
            } else{
                return false;
            }

            start++;
            end--;
        }

        return isReturnToOrigin;
    }
}
