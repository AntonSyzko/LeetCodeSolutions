package leet.code.solutions.arrays;

import java.util.List;

/*
 from each index we can jump to the index of its value
  ex: {1,2,0}
 so from index 0 we can jump 1 step ahead ( to index 1 )
 from index 1 we can jump 2 steps ahead ( to index 3 )
 from index 2 we can jump 0(zero) steps
  */

public class AdvancingThroughArray {

    public static void main(String[] args) {
        //   List<Integer> steps = List.of(2, 4, 1, 1, 0, 2, 3);
        //List<Integer> steps2 = List.of(3, 3, 1, 0, 2, 0, 1);
        List<Integer> steps = List.of(1, 1, 0);
        boolean canReachEnd = canReachEnd(steps);
        System.out.println(canReachEnd);

         steps = List.of(0, 1, 1);
        canReachEnd = canReachEndUsingWhile(steps);
        System.out.println(canReachEnd);

    }

    private static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestIndexWeCanReachSoFar = 0;
        int lastIndex = maxAdvanceSteps.size() - 1;

        //currentIndex <= furthestIndexWeCanReachSoFar - if heave valid  step at ALL ?
        // if current Index is bigger than we could jump from previous - then it's not possible to end already

        // furthestIndexWeCanReachSoFar < lastIndex = we have a  room to still increment,
        // if bigger than we have reached the result already - no need to iterate more
        for (int currentIndex = 0; currentIndex <= furthestIndexWeCanReachSoFar && furthestIndexWeCanReachSoFar < lastIndex; currentIndex++) {

            //from current index to it's value - to which next index we jump from here
            int currentJumpDistance = currentIndex + maxAdvanceSteps.get(currentIndex);

            furthestIndexWeCanReachSoFar = Math.max(furthestIndexWeCanReachSoFar, currentJumpDistance);
        }
        //did we overlap end - if so - TRUE
        return furthestIndexWeCanReachSoFar >= lastIndex;

    }

    private static boolean canReachEndUsingWhile(List<Integer> maxAdvanceSteps) {
        int currentIndex = 0;
        int indexOfEnd = maxAdvanceSteps.size() -1;
        int maxJumpSoFar = 0;

         //currentIndex <= maxJumpSoFar - if bigger then we could not reach it from previous jump at all
        // maxJumpSoFar < indexOfEnd - if bigger then result already reached - no need to itetrate
        while (currentIndex <= maxJumpSoFar && maxJumpSoFar < indexOfEnd){

            //currentIndex + maxAdvanceSteps.get(currentIndex) is the current possible jump size from current index
            maxJumpSoFar = Math.max(maxJumpSoFar, currentIndex + maxAdvanceSteps.get(currentIndex));

            currentIndex++;//iterate onward
        }

        return maxJumpSoFar >= indexOfEnd;//if overlap END - TRUE

    }


    }
