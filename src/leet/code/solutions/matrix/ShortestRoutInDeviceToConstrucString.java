package leet.code.solutions.matrix;

/*
https://www.techiedelight.com/find-shortest-route-device-construct-given-string/

Given a device having left, right, top, and bottom buttons and an OK button to enter a text from a virtual keypad
having alphabets from A–Y arranged in a 5 × 5 grid, as shown below.

Find the shortest route in the device to construct a given string if we start from the top-left position in the keypad.

For example,

Alphabet Keypad: ( 5 * 5)
Keypad:
        A  B  C  D  E
        F  G  H  I  J
        K  L  M  N  O
        P  Q  R  S  T
        U  V  W  X  Y


Device:         L
             T  M  R
                B

where, T — Move upB — Move downL — Move leftR — Move rightM — Press OK


The shortest route to construct string TECHIE with the device’s help is BBBRRRRMTTTMLLMBMRMTRM.
 */
public class ShortestRoutInDeviceToConstrucString {

    public static void main(String[] args) {
        String str = "TECHIE";
        printPath(str);
    }

    private static void printPath(String str) {
        //start top Left of the keypad
        int start_X_coordinate = 0;
        int start_Y_Coordinate = 0;

        for (char currLetter : str.toCharArray()) {
            int currentX_Coordinate =  (currLetter - 'A') / 5;
            int currentY_Coordinate =  (currLetter - 'A') % 5;

            while( start_X_coordinate > currentX_Coordinate){
                System.out.print("T");
                start_X_coordinate--;
            }

            while( start_X_coordinate < currentX_Coordinate){
                System.out.print("B");
                start_X_coordinate++;
            }

            while( start_Y_Coordinate > currentY_Coordinate){
                System.out.print("L");
                start_Y_Coordinate--;
            }

            while( start_Y_Coordinate < currentY_Coordinate){
                System.out.print("R");
                start_Y_Coordinate++;
            }

            System.out.print("M");
        }
    }
}
