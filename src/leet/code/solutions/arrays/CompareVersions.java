package leet.code.solutions.arrays;

/*
Compare two version numbers version1 and version2. If version1 >version2 return 1,
if version1 <version2 return -1, otherwise return 0.
You may assume that the version strings are non-empty and contain only digits and
the . character. The . character does not represent a decimal point and is used to
separate number sequences.
Here is an example of version numbers ordering:
0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersions {

    public static void main(String[] args) {
        String v1 = "0.1";
        String v2 = "1.1";

        int compared = compareVersion(v1, v2);
        System.out.println(compared);

         v1 = "1.1.1";
         v2 = "1.1";

         compared = compareVersion(v1, v2);
        System.out.println(compared);
    }

    //O(n + m) / O(n + m)
    public static int compareVersion(String version1, String version2) {

        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        int maxLen = Math.max(v1Parts.length, v2Parts.length);

        for (int i = 0; i < maxLen; i++) {

            // Get version part or default to 0 if not present
            int num1 = i < v1Parts.length ?  Integer.parseInt(v1Parts[i]) : 0;
            int num2 = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;

            if(num1 > num2){
                return 1;
            }else if ( num1 < num2){
                return -1;
            }
            // If equal, continue to next part

        }

        return 0;
    }
    }
