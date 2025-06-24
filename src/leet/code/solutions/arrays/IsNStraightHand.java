package leet.code.solutions.arrays;

import java.util.*;

/*
https://leetcode.com/problems/hand-of-straights/description/

given array of cards represnted as int[] - and W number of allowed subsets
arrange cards in W subsets placing only CONSECUTIVE numbers together

[ 1, 2 , 3, 6, 2, 3, 4, 7, 8 ]
W = 3

[1,2,3]
[2,3,4]
[6,7,8]

 */
public class IsNStraightHand {

    public static void main(String[] args) {
     int[] cards = { 1, 2 , 3, 6, 2, 3, 4, 7, 8};
     boolean isStraightHandPossible = isNStraightHand(cards,3);
        System.out.println(isStraightHandPossible);
    }

    /*
    Time Complexity: O(n log n)

        Building TreeMap: O(n log n) - each insertion takes O(log n)
        Main loop: O(n/W) iterations × O(W log n) per iteration = O(n log n)

        Each iteration processes W consecutive cards
        TreeMap operations (get, put, remove) are O(log n)



Space Complexity: O(n)

        TreeMap stores at most n unique cards
     */

    private static boolean isNStraightHand(int[] cards, int allowedSubsetsCount) {

        if(cards.length % allowedSubsetsCount != 0) return false;//if MOD is not 0 -> cannot break into allowed sizes

           TreeMap<Integer, Integer> cardToOccurrencesMap = new TreeMap<>();

           for (int card: cards) {
               //card : times it has occurred in all cards
               cardToOccurrencesMap.put(card,cardToOccurrencesMap.getOrDefault(card,0) + 1);
           }

           while (cardToOccurrencesMap.size() > 0) {

               int firstCard = cardToOccurrencesMap.firstKey();

               //firstCard here is a value on card and we iterate (firstCard + allowedSubsetsCount ) times to check if numbers are consecutive ( +1,+1 etc ...)
               for (int currentCard = firstCard; currentCard < firstCard + allowedSubsetsCount; currentCard++) {

                   //currentCard is inceremented by 1 each time , and we limit it to allowedSubsetsCount times
                   if(!cardToOccurrencesMap.containsKey(currentCard)){
                       return false;//if incremented by 1 is not in map - no way we can have a hand of consecutive cards
                   }

                   int currentCardOccurrenceCount = cardToOccurrencesMap.get(currentCard);

                   if (currentCardOccurrenceCount == 1) {//if this card only once in the entire set

                       cardToOccurrencesMap.remove(currentCard);//drop is as we have already checked consecutive hand

                   }else{//otherwise decrement by 1 as we can build another hand in future

                       cardToOccurrencesMap.replace(currentCard, currentCardOccurrenceCount - 1);
                   }
               }
           }
           return true;
    }

    //----------------- optimised --------------------
    // Simple and readable version
    private static boolean isNStraightHandSimple(int[] cards, int allowedSubsetsCount) {
        if (cards.length % allowedSubsetsCount != 0) return false;

        // Count frequencies
        Map<Integer, Integer> cardToOccurrencesMap = new HashMap<>();
        for (int card : cards) {
            cardToOccurrencesMap.put(card, cardToOccurrencesMap.getOrDefault(card, 0) + 1);
        }

        // Process cards in sorted order
        List<Integer> sortedCards = new ArrayList<>(cardToOccurrencesMap.keySet());
        Collections.sort(sortedCards);

        for (int card : sortedCards) {

            int frequency = cardToOccurrencesMap.get(card);

            if (frequency == 0){//no such a card anymore
                continue;
            }

            // Try to form consecutive groups starting from this card
            for (int i = 0; i < allowedSubsetsCount; i++) {
                int nextCard = card + i;
                int nextFreq = cardToOccurrencesMap.getOrDefault(nextCard, 0);

                if (nextFreq < frequency) {
                    return false;
                }

                cardToOccurrencesMap.put(nextCard, nextFreq - frequency);//update count - distract frequency from curr card value
            }
        }
        return true;
    }

}
