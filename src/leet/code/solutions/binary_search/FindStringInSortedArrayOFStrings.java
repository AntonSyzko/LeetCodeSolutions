package leet.code.solutions.binary_search;

/*
Given sorted array of strings with empty strings in it
get first occurrence of STR

STR = 'cat"

array [ "at" , "", "cat", "", "", "", "", "dall","","doll"}
 */
public class FindStringInSortedArrayOFStrings {

    public static void main(String[] args) {
        String lookupSTr = "cat";
        String[] array = { "at" , "", "cat", "", "", "", "", "dall","","doll"};

        int position = search(array, lookupSTr);

        System.out.println(position);
    }

    private static int search(String[] array, String lookupSTr) {
        if(lookupSTr == null || array == null || lookupSTr.isEmpty() ) return -1;
        return  searchForLookupStr(array, lookupSTr, 0, array.length-1);
    }

    private static int searchForLookupStr(String[] array, String lookupSTr, int start, int end) {

        if(start > end) return -1;

        int mid = start + (end - start)/2;

        if(array[mid].isEmpty()){
             mid = getClosestNonEmptyString(array, start, mid, end);
        }

        if(mid == -1) {
            return -1;
        }

        if(array[mid].equals(lookupSTr)) {
            return mid;
        }

        return array[mid].compareTo(lookupSTr) > 0 ?
                //by MID is too big
                searchForLookupStr(array,lookupSTr,start, mid-1)//look at left
                :
                //by mid is too small
                searchForLookupStr(array, lookupSTr, mid + 1 , end);//look at right 

    }

    private static int getClosestNonEmptyString(String[] array, int start, int mid, int end) {
        int left = mid -1;
        int right = mid +1;

        //start<------------    ------------> end
        while (left >= start  && right <= end) {

            if(left >= start && !array[left].isEmpty()){//closest found at left
                 return left;
            }

            if(right <= end && !array[right].isEmpty()){//closest found at right
                return right;
            }

            left--;
            right++;
        }

        return -1;//not found in these boundaries

    }
}
