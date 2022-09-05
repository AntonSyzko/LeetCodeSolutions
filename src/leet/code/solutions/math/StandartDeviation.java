package leet.code.solutions.math;

import java.util.stream.IntStream;

/*

https://www.mathsisfun.com/data/standard-deviation-formulas.html
To calculate the standard deviation of those numbers:

1. Work out the Mean (the simple average of the numbers)
2. Then for each number: subtract the Mean and square the result
3. Then work out the mean of those squared differences. ( divide by N for standart and by N-1 for sample deviation )
4. Take the square root of that and we are done!
 */
public class StandartDeviation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        double standartDeviation = countStandardDeviation(nums);
        System.out.println(standartDeviation);
    }

    private static double countStandardDeviation(int[] nums) {
        //Work out the Mean (the simple average of the numbers)
        double mean = workOutMean(nums);

        //Then for each number: subtract the Mean and square the result
        int[] meanSubstracted = substractMean(nums, mean);

        //Then work out the mean of those squared differences.
        double meanOfSquaredSubstracted = workOutMean(meanSubstracted);

        //Take the square root of that and we are done!
        double squareRootResult = Math.sqrt(meanOfSquaredSubstracted);


        double meanOfSquaredSubstractedSample = workOutSampleMean(meanSubstracted);
        double sampleDeviation = Math.sqrt(meanOfSquaredSubstractedSample);
        System.out.println("Sample " + sampleDeviation);


        return squareRootResult;
    }


    private static double workOutMean(int[] nums) {
        return IntStream.of(nums).boxed().mapToInt(x -> x).average().getAsDouble();
    }

    private static int[] substractMean(int[] nums, double mean) {
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = (int) Math.pow((nums[i] - mean), 2);
        }
        return res;
    }


    private static double workOutSampleMean(int[] meanSubstracted) {
        int sum = IntStream.of(meanSubstracted).sum();
        return sum / meanSubstracted.length - 1;
    }


}
