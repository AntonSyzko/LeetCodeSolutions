package leet.code.solutions.strings;

public class ConvertToLowerCase {

    public static void main(String[] args) {
      String  str  =  "HellO";
      System.out.println(convert(str));

    }

    private static String convert(String str) {
        StringBuilder lowered = new StringBuilder();


        for(char each : str.toCharArray()) {

            if(Character.isUpperCase(each)) {
                lowered.append((char) (each + 32));
            }else{
                lowered.append(each);
            }
        }

        return lowered.toString();
    }
}
