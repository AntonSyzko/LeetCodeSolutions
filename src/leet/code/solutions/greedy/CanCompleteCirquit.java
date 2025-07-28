package leet.code.solutions.greedy;

import java.util.Arrays;

/*

https://neetcode.io/problems/gas-station


There are n gas stations along a circular route. You are given two integer arrays gas and cost where:

gas[i] is the amount of gas at the ith station.
cost[i] is the amount of gas needed to travel from the ith station to the (i + 1)th station. (The last station is connected to the first station)
You have a car that can store an unlimited amount of gas, but you begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index such that you can travel around the circuit once in the clockwise direction. If it's impossible, then return -1.

It's guaranteed that at most one solution exists.

Example 1:

Input: gas = [1,2,3,4], cost = [2,2,4,1]

Output: 3
Explanation: Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 1 + 1 = 3
Travel to station 1. Your tank = 3 - 2 + 2 = 3
Travel to station 2. Your tank = 3 - 2 + 3 = 4
Travel to station 3. Your tank = 2 - 4 + 4 = 2
 */
public class CanCompleteCirquit {

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {1,3,2,4,5};

        int can = canCompleteCircuit(gas, cost);
        System.out.println(can);
    }

    private static int canCompleteCircuit(int[] gas, int[] cost) {

        int gasSum = Arrays.stream(gas).sum();
        int costSum = Arrays.stream(cost).sum();

        if(gasSum < costSum){//if sum of gas is LESS than sum of cost - no way there is match
            return -1;
        }

       int totalGas = 0;

       int resIndex = 0;

        for (int i = 0; i < gas.length; i++) {

            totalGas += (gas[i] - cost[i]);

            if(totalGas < 0){//total gas dropped below 0 -> we'll have to start over from i
                totalGas = 0;//reset
                resIndex = i + 1;//move next , this one is negative and negative does not give us way to move forward
            }
        }

        return resIndex;//result is guaranteed since the task says there is one result
    }
    }
