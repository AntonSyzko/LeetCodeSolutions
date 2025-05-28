package leet.code.solutions.arrays;

import java.util.Set;

/*
isi
 */
public class RemoveVowels {

    public static void main(String[] args) {
        String s = "aerobica";
        String v = removeVowels(s);
        System.out.println(v);
    }

    private  static String removeVowels(String str){
        return str.replaceAll("[aeiouAEIOU]","");
    }

    private  static String removeVowels2(String str){

        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!vowels.contains(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
