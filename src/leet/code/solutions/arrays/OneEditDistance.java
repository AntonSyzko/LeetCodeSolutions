package leet.code.solutions.arrays;

/*

Given two strings S and T, determine if they are both one edit distance apart
 */
public class OneEditDistance {

    public static void main(String[] args) {
        String s = "test";
        String t = "testA";

        boolean res = isOneEditDistance(s, t);
        System.out.println(res);

         s = "test";
         t = "testAA";

         res = isOneEditDistance(s, t);
        System.out.println(res);
    }

    private static boolean isOneEditDistance(String s, String t) {
        if(s==null || t==null){
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();

        if(Math.abs(sLen - tLen) > 1){//distance is already > 1
            return false;
        }

        int sIndex = 0;
        int tIndex = 0;

        int count=0;

        while(sIndex < sLen && tIndex < tLen){//AND

            if(s.charAt(sIndex)==t.charAt(tIndex)){//chars are same

                sIndex++;
                tIndex++;

            }else{//chars differ

                count++;

                if(count > 1){
                    return false;//fail fast
                }

                if(sLen > tLen){

                    sIndex++;

                }else if(sLen < tLen){

                    tIndex++;

                }else{//length is same

                    //advance both
                    sIndex++;
                    tIndex++;
                }

            }
        }//while end

        //remainder after while exited
        if(sIndex < sLen || tIndex < tLen){//OR
            count++;
        }

        return count == 1;
    }
}