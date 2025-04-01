package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.Map;

public class RomsnToInteger {

    public static void main(String[] args) {
         String roman = "XIV";
         int arabic =  romanToInteger(roman);
         System.out.println(arabic);
    }

    private static int romanToInteger(String romanNum) {

        Map<Character, Integer> convertor = new HashMap<>();
        convertor.put('I', 1);
        convertor.put('V', 5);
        convertor.put('X', 10);
        convertor.put('L', 50);
        convertor.put('C', 100);
        convertor.put('D', 500);
        convertor.put('M', 1000);

        int arabicResult = 0;
        int prevVal = 0;


        for (int romanLetter = romanNum.length() - 1 ; romanLetter >= 0 ; romanLetter--) {//traverse backwards

            int currentVal = convertor.get(romanNum.charAt(romanLetter));//get arabic representation from convertor map

            if(currentVal < prevVal){//if current LESS than prev

                arabicResult = arabicResult - currentVal;//extract

            }else{ // if current BIGGER than prev

                arabicResult = arabicResult + currentVal;// add

            }

            prevVal = currentVal;//update prev to what was current
        }

        return arabicResult;
    }
}
