package leet.code.solutions.bitwise;

public class ToPowerOf {

    public static void main(String[] args) {
      double toPower = 2.0;
      int power = 3;
      double result = power(toPower, power);
        System.out.println(result);
    }

    private static double power(double x, int y) {
        double result = 1.0;
        long power = y;

        if (y < 0) {
            power = -power;
            x = 1.0 / x;
        }

        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
            }
            x *= x;
            power >>>= 1;
        }
        return result;
    }
}
