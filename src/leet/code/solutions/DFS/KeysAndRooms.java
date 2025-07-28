package leet.code.solutions.DFS;

import java.util.*;

/*
https://leetcode.com/problems/keys-and-rooms/description/

There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

Example 1:

Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation:
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.

Example 2:

Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.

Constraints:

n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
 */
public class KeysAndRooms {

    public static void main(String[] args) {
         List<List<Integer>> rooms = new ArrayList<>();
         rooms.add(List.of(1,3));
         rooms.add(List.of(3,0,1));
         rooms.add(List.of(2));
         rooms.add(List.of(0));

         boolean canVisit = canVisitAllRoomsRecursion(rooms);
         System.out.println(canVisit);

         rooms = new ArrayList<>();
        rooms.add(List.of(1));
        rooms.add(List.of(2));
        rooms.add(List.of(3));
        rooms.add(Collections.emptyList());

         canVisit = canVisitAllRoomsRecursion(rooms);
        System.out.println(canVisit);
    }
    /*
    The time complexity is O(n + k) where n is the number of rooms and k is the total number of keys across all rooms. Space complexity is O(n) for the visited array and the recursion stack.
     */

    private static boolean canVisitAllRoomsRecursion(List<List<Integer>> rooms) {

        if(rooms == null || rooms.isEmpty()) return true;

        boolean[] visited = new boolean[rooms.size()];

        int startRoom = 0;

        dfs(rooms, startRoom, visited);

         for(boolean bool : visited){
             if(!bool) return false;//if any cell is not visisted after all - no way to visit ALL rooms
         }

         return true;
    }

    private static void dfs(List<List<Integer>> rooms, int startRoom, boolean[] visited) {
        if(visited[startRoom]) {
            return;
        }

        visited[startRoom] = true;

         for(int room : rooms.get(startRoom)) {
             dfs(rooms, room, visited);
         }
    }


    /*
      The time and space complexity of your solution are both O(n + k),
      where n is the number of rooms and k is the total number of keys, which is optimal for this problem.
     */

    private static boolean canVisitAllRooms(List<List<Integer>> rooms) {

        Set<Integer> visited = new HashSet<>();
        visited.add(0);

         Stack<Integer> stack = new Stack<>();
         stack.push(0);

         while (!stack.isEmpty()) {

             int currentRoomIndex = stack.pop();

             List<Integer> currentRoomAllKeys = rooms.get(currentRoomIndex);

             for(int room : currentRoomAllKeys) {

                 if(!visited.contains(room)){
                     visited.add(room);
                     stack.push(room);
                 }

             }
         }

         return visited.size() == rooms.size();
    }

    }
