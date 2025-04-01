package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.TreeMap;

/*given array of cards represnted as int[] - and W number of allowed subsets
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

    private static boolean isNStraightHand(int[] cards, int allowedSubsetsCount) {
           TreeMap<Integer, Integer> cardToOccurrencesMap = new TreeMap<>();

           for (int card: cards) {
               cardToOccurrencesMap.put(card,cardToOccurrencesMap.getOrDefault(card,0) + 1);
           }

           while (cardToOccurrencesMap.size() > 0) {

               int firstCard = cardToOccurrencesMap.firstKey();

               //firstCard here is a value on card amd wwe iterate (firstCard + allowedSubsetsCount ) times to check if numbers are consecutive ( +1,+1 etc ...)
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
}
