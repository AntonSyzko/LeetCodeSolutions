package leet.code.solutions.base_conversions;
/*
                    ENCODE
    while(number > 0)
    remainder = number % 62
    number = number / 62
    attach remainder to start of result collection

    1st iteration:
         number = 1000
         remainder = 1000 % 62 = 8
         number = 1000 / 62 = 16
         result list = [8]
    2nd iteration:
         number = 16
         remainder = 16 % 62 = 16
         number = 16 / 62 = 0
         result list = [16,8]
         There is no more iterations since number = 0 after 2nd iteration


                             DECODE
         i = 0
         while(i < inputString lenght)
         counter = i + 1
         mapped = base62alphabet.indexOf(inputString[i]) // map character to number based on its index in alphabet
         result = result + mapped * 62^(inputString lenght - counter)

        inputString = g8
        inputString length = 2
        i = 0
        result = 0
        1st iteration
             counter = 1
             mapped = 16 // index of g in base62alphabet is 16
             result = 0 + 16 * 62^1 = 992
        2nd iteration
              counter = 2
              mapped = 8 // index of 8 in base62alphabet is 8
              result = 992 + 8 * 1^62 = 1000

 */

public class Base62 {

    private static final String ALPHABET_BASE_62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final char[] ALLOWED_CHARACTERS = ALPHABET_BASE_62.toCharArray();
    private static final int BASE = 62;

    public static void main(String[] args) {
        long input = 1000L;
        String encoded = encode(input);
        long decoded = decode(encoded);
        System.out.println(encoded);
        System.out.println(decoded);
    }

    /*
    while(number > 0)
    remainder = number % 62
    number = number / 62
    attach remainder to start of result collection
     */
    private static String encode(long input) {
        var encodedString = new StringBuilder();

        if (input == 0) {
            return String.valueOf(ALLOWED_CHARACTERS[0]);
        }

        while (input > 0) {
            encodedString.append(ALLOWED_CHARACTERS[(int) (input % BASE)]);
            input = input / BASE;
        }

        //reverse cause attach remainder to start of result collection
        return encodedString.reverse().toString();//
    }
    /*
            i = 0
        while(i < inputString lenght)
         counter = i + 1
         mapped = base62alphabet.indexOf(inputString[i]) // map character to number based on its index in alphabet
         result = result + mapped * (inputString lenght - counter) ^ 62
     */

    private static long decode(String input) {
        var decoded = 0;

        var characters = input.toCharArray();
        var length = characters.length;

        //counter is used to avoid reversing input string
        var counter = 1;

        for (int i = 0; i < characters.length; i++) {
            decoded += ALPHABET_BASE_62.indexOf(characters[i]) * Math.pow(BASE, length - counter);
            counter++;
        }

        return decoded;
    }
}
