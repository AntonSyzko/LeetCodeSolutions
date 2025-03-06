package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

   static Map<Integer, String> dictionary = new HashMap<>();



    public static void main(String[] args) {
        String number = "23";


        dictionary.put(2, "abc");
        dictionary.put(3, "def");
        dictionary.put(4, "ghi");
        dictionary.put(5, "jkl");
        dictionary.put(6, "mno");
        dictionary.put(7, "pqrs");
        dictionary.put(8, "tuv");
        dictionary.put(9, "wxyz");


        List<String> combinations= letterCombinations(number);
        System.out.println(combinations);

    }



    private static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        String [] mappings =  {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};//dictionary

        printCurrentCombinations(result, digits, "", 0, mappings);


        return result;
    }

    private static void printCurrentCombinations(List<String> result, String digits, String currentCombination, int indexInCurrentDigit, String[] mappings) {

        //BASE
        if(indexInCurrentDigit == digits.length()){//reached end
            result.add(currentCombination);
            return;
        }


        String letters = mappings[digits.charAt(indexInCurrentDigit) - '0'];

        for (int i = 0; i < letters.length() ; i++) {
            printCurrentCombinations(result, digits, currentCombination + letters.charAt(i), indexInCurrentDigit + 1, mappings);
        }
    }


    private static void printCombinationsBruteForce(String digit, Map<Integer,String> dict) {

        char[] digits = digit.toCharArray();

        for (int i = 0; i < digits.length; i++) {
            int currDIg = (int) digits[i] - '0';
            for (int j = i+1; j < digits.length; j++) {
                int nextDigit = (int) digits[j] - '0';
                printCombinationBruteF(currDIg, nextDigit, dict);
            }
        }
    }

    private static void printCombinationBruteF(int currDIg, int nextDigit, Map<Integer, String> dict) {

        System.out.println("\r\n\t curr dig " + currDIg + " nextDigit " + nextDigit);

        char[] lettersOfCurrDigit = dict.get(currDIg).toCharArray();
        char[] lettersOfNextDigiet = dict.get(nextDigit).toCharArray();

        for (int i = 0; i < lettersOfNextDigiet.length; i++) {
            for (int j = 0; j < lettersOfNextDigiet.length; j++) {
                String s = String.valueOf(lettersOfCurrDigit[i]) + String.valueOf(lettersOfNextDigiet[j]);
                System.out.print("[" + s + "], ");

            }
        }
    }
}
