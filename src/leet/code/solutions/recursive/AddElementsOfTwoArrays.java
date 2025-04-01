package leet.code.solutions.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.techiedelight.com/add-elements-two-arrays/

Given two arrays of positive integers, add their elements into a new array.
The solution should add both arrays, one by one starting from the 0th index, and split the sum into individual digits if it is a 2–digit number.

For example,

Input:
 a = { 23, 5, 2, 7, 87 }
 b = { 4, 67, 2, 8 }
  Output: { 2, 7, 7, 2, 4, 1, 5, 8, 7 }

   Input: a = {}
          b = { 4, 67, 2, 8 }
   Output: { 4, 6, 7, 2, 8 }


 */
public class AddElementsOfTwoArrays {

    public static void main(String[] args) {
        int[] array1 = {23,5,2,7,87};
        int[] array2 = { 4, 67, 2, 8};

        int[] res = addTwoArrays(array1, array2);
        List<Integer> resList = addTwoArraysList(array1, array2);

        System.out.println(Arrays.toString(res));
        System.out.println(resList);

        int[] array11 = {};
        int[] array22 = { 4, 67, 2, 8};

        int[] res2 = addTwoArrays(array11, array22);
        List<Integer> res2List = addTwoArraysList(array11, array22);

        System.out.println(Arrays.toString(res2));
        System.out.println(res2List);

    }

    private static List<Integer> addTwoArraysList(int[] array1, int[] array2) {
        List<Integer> res = new ArrayList<>();

        doAdditionList(array1, 0, array2, 0, res );

        return res;
    }

    private static void doAdditionList(int[] array1, int indexOfArray1, int[] array2, int indexOfArray2, List<Integer> res) {
        if(indexOfArray1 >= array1.length && indexOfArray2 >= array2.length){//BASE both arrays exhausted
            return;
        }

        int currentSum = 0;

        if(indexOfArray1 < array1.length && indexOfArray2 < array2.length){//both yet have smth to add

            currentSum  = array1[indexOfArray1] + array2[indexOfArray2];

           splitSumInTwoList(currentSum, res);

            indexOfArray1++;
            indexOfArray2++;

        } else if (indexOfArray1 == array1.length && indexOfArray2 < array2.length) { // second still has smth

            currentSum = array2[indexOfArray2];
            splitSumInTwoList(currentSum, res);
            indexOfArray2++;

        } else{//first still have smth

            currentSum = array1[indexOfArray1];
            splitSumInTwoList(currentSum, res);
            indexOfArray1++;
        }

        doAdditionList(array1,indexOfArray1,array2,indexOfArray2,res);//recur with array1 and array2 indexes moved
    }

    private static void splitSumInTwoList(int currentSum, List<Integer> res){
        if(currentSum / 10 > 0){
            int firstDigit = currentSum / 10;
            int secondDigit = currentSum % 10;
            res.add(firstDigit );
            res.add(secondDigit );
        }else{
            res.add(currentSum);
        }
    }

    // ----without list - just array but trailing zeros

    private static int[] addTwoArrays(int[] array1, int[] array2) {
        int[] res = new int[array1.length*2 + array2.length*2];

        int startingIndexInRes = 0;

        doAddition(array1, 0, array2, 0, res, startingIndexInRes );

        return res;
    }

    private static void doAddition(int[] array1, int indexOfArray1, int[] array2, int indexOfArray2, int[] res, int startingIndexInRes) {
        if(indexOfArray1 >= array1.length && indexOfArray2 >= array2.length){//BASE
            return;
        }

        int currentSum = 0;

        if(indexOfArray1 < array1.length && indexOfArray2 < array2.length){

            currentSum  = array1[indexOfArray1] + array2[indexOfArray2];

           startingIndexInRes =  splitSumInTwo(currentSum, res, startingIndexInRes);

            indexOfArray1++;
            indexOfArray2++;
        } else if (indexOfArray1 == array1.length && indexOfArray2 < array2.length) {
            currentSum = array2[indexOfArray2];
            startingIndexInRes =  splitSumInTwo(currentSum, res, startingIndexInRes);
            indexOfArray2++;
        } else{
            currentSum = array1[indexOfArray1];
            startingIndexInRes =  splitSumInTwo(currentSum, res, startingIndexInRes);
            indexOfArray1++;
        }

        doAddition(array1,indexOfArray1,array2,indexOfArray2,res,startingIndexInRes);//recur
    }

    private  static int  splitSumInTwo(int currentSum, int[] res, int indexInRes){

        if(currentSum / 10 > 0){
            int firstDigit = currentSum / 10;
            int secondDigit = currentSum % 10;
            res[indexInRes] = firstDigit ;
            indexInRes++;
            res[indexInRes] = secondDigit ;
            indexInRes++;
        }else{
            res[indexInRes] = currentSum ;
            indexInRes++;
        }

        return indexInRes;
    }
}
