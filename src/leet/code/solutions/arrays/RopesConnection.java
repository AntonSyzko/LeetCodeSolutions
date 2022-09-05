package leet.code.solutions.arrays;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
Найти минимальную стоимость соединения данных верёвок в одну, где стоимость соединения двух верёвок равна сумме их длин.

В качестве примера рассмотрим два сценарии соединения четырёх верёвок, длины которых равны 10, 5, 21 и 3.

Сначала соединим 10 и 5, потом соединим 21 и 3, после чего соединим две полученные верёвки. Стоимость: 15+24+39=78.

Сначала соединим 3 и 5 — получим верёвку длины 8. После этого соединим 8 и 10 — получим верёвку длины 18. Наконец, соединим 18 и 21. Стоимость: 8+18+39=65.
 */
public class RopesConnection {
    public static void main(String[] args) {
        List<Integer> ropes = List.of(3,5,1,2,6);
        int connectionOptimal = connectRopesWithMinimalCost(ropes, 2);
        System.out.println(connectionOptimal);

        int [] ropess = {3,5,1,2,6};
         connectionOptimal = minCostMinHeap(ropess, 2);
        System.out.println(connectionOptimal);
    }

    //O(nLogn)
    static int minCostMinHeap(int ropes[]) {//only connecting TWO ropes
        
        int minCostConnection = 0;
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int each : ropes) {
            minHeap.add(each);
        }

        while (minHeap.size() > 1) {
            
            int firstMinRope = minHeap.poll();
            int secondMinRope = minHeap.poll();
            int currentTwoMinRopesSum = firstMinRope + secondMinRope;
            
            minHeap.add(currentTwoMinRopesSum);
            minCostConnection += currentTwoMinRopesSum;
        }
        
        return minCostConnection;
    }

    //O(nLogn)
    static int minCostMinHeap(int [] ropes, int priceToConnectRopes) {

        int minCostConnection = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int each : ropes) {
            minHeap.add(each);
        }

        while (minHeap.size() > 1) {

            int currentRopesSum = 0;
            int sumToAdd = 0;

            for (int i = 0; i < priceToConnectRopes; i++) {
                 sumToAdd += minHeap.poll();
            }

            currentRopesSum += sumToAdd;

            minHeap.add(currentRopesSum);
            minCostConnection += currentRopesSum;

        }

        return minCostConnection;
    }


    //O(n logN)
    private static int connectRopesWithMinimalCost(List<Integer> ropes, int priceToConnectRopes) {
        int aggregatedSums = 0;
        int currentSum = 0;

        while (ropes.size() > 1) {
            currentSum = ropes.stream().sorted().limit(priceToConnectRopes).mapToInt(Integer::intValue).sum();//connect two shortest ones

            ropes = ropes.stream().sorted().skip(priceToConnectRopes).collect(Collectors.toList());//remove 2 agrregated previously ropes

            ropes.add(currentSum);//add previously connected result

            aggregatedSums += currentSum;//aggregate results of previously connected ropes as you go
        }

        return aggregatedSums;
    }
}
