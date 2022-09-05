package leet.code.solutions.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stats {
    static Bag<Integer> bag = new Bag<>();

    public static void main(String[] args) {
       pupulateBag(bag);
       printStats(bag);
    }

    private static void printStats(Bag<Integer> bag){
        int sizeOfBag = bag.getSizeOfBag();

        double sum = 0.0;

        for (int each : bag.nums){
            sum += each;
        }

        double mean  = sum / sizeOfBag;

        System.out.println("Sum: " + sum);
        System.out.println("Mean: " + mean);


        sum = 0.0;//reset sum for standart deviation calc
        double standartDeviation = 0.0;

        //STD DEVIATION = sum += (each - mean) * (each-mean)
        //then SQRT (sum/total numbers size -1
        //this for each item
        for (int each : bag.nums) {
            sum += (each - mean) * (each - mean);
            standartDeviation = Math.sqrt(sum/(sizeOfBag - 1));
        }
        System.out.println("Standart deviation: " + standartDeviation);


    }

    private static void pupulateBag(Bag<Integer> bag){
        Random rnd = new Random();
        for (int i = 0; i < 10 ; i++) {
            bag.addToBag(rnd.nextInt(100));
        }
    }

    private static class Bag<N>{

        List<N> nums = new ArrayList<>();

         private void addToBag(N itemToAdd){
             nums.add(itemToAdd);
         }

         private int getSizeOfBag(){
             return nums.size();
         }

        @Override
        public String toString() {
            return "Bag{" +
                "nums=" + nums +
                '}';
        }
    }
}
