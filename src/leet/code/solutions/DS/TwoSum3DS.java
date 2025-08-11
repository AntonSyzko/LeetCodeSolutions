package leet.code.solutions.DS;

import java.util.HashMap;
import java.util.Map;

/*
The problem requires the design of a data structure to manage a stream of integers and provides two functionalities.

Adding an integer to the collection.

Querying to find out if there are any two DISTINCT !!!  integers already in the collection that add up to a specified target sum.

This data structure should efficiently support the insertion of numbers and the checking for the existence of a pair of numbers that meet the target sum condition.
 */
public class TwoSum3DS {

    private final Map<Integer, Integer> counts ;

    public TwoSum3DS() {
        counts = new HashMap<>();
    }

    // Adds the input number to the data structure.
    public void add(int number) {
        counts.put(number, counts.getOrDefault(number, 0) + 1);
    }

    public boolean find(int targetSum) {

        for (Map.Entry<Integer,Integer> entry : counts.entrySet()){
            int key = entry.getKey();
            int keyOccurrenceCount = entry.getValue();

            int lookupDiff = targetSum - key;

            if( key != lookupDiff && counts.containsKey(lookupDiff)){//lookup diff exists AND it is not same as KEY ( as we have to use DISTINCT numbers)
                return true;
            }

            if(key == lookupDiff && keyOccurrenceCount > 1){// key AND lookup are same but key exists more than 1 time - hence we can use DISTINCT different exemplars of key
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TwoSum3DS twoSum3DS = new TwoSum3DS();
        twoSum3DS.add(1);
        twoSum3DS.add(2);
        twoSum3DS.add(3);
        twoSum3DS.add(4);
        twoSum3DS.add(1);

        System.out.println( twoSum3DS.find(2));

    }
    }