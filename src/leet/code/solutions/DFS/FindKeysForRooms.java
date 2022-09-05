package leet.code.solutions.DFS;

import java.util.*;

//leetcode 841
/*
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it.
Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the
other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
return true if you can visit all the rooms, or false otherwise.
 */
public class FindKeysForRooms {


    public static void main(String[] args) {
        List<List<Integer>> rooms = Arrays.asList(List.of(1), List.of(2), List.of(3), List.of(0));
        boolean canVisit = canVisitAllRooms(rooms);
        System.out.println(canVisit);

        List<List<Integer>> rooms2 = Arrays.asList(List.of(1), List.of(3), List.of(2), List.of(0));
        boolean canVisit2 = canVisitAllRooms(rooms2);
        System.out.println(canVisit2);


        List<List<Integer>> rooms3 = Arrays.asList(List.of(1), List.of(2), List.of(2), List.of(0));
        boolean canVisit3 = canVisitAllRooms(rooms3);
        System.out.println(canVisit3);
    }



        public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visitedRoomsBacktracking = new HashSet<>();//backtrack what has been visited
        visitedRoomsBacktracking.add(0);//by default we visit ZERO index room from list

        Stack<Integer> dfsStack = new Stack<>();//DFS storage
        dfsStack.add(0);//default 0 index room we are at

        while (!dfsStack.isEmpty()){ //while in DFS stack something

            List<Integer> keysFoundInCurrentRoom = rooms.get(dfsStack.pop());//get keys stored in the room we are currently visiting
            //keys ARE for the other room !!!
            for (int key : keysFoundInCurrentRoom){ //for each key check
                if(!visitedRoomsBacktracking.contains(key)) { // if NOT YET visited
                    visitedRoomsBacktracking.add(key); //add to visit
                    dfsStack.add(key);// add to DFS as back track where we are
                }
            }
        }
        return visitedRoomsBacktracking.size() == rooms.size(); //if size of visited = size of all romms - we  have successfully traversed all romms
    }
}
