package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/find-the-town-judge/

In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1

Constraints:

1 <= n <= 1000
0 <= trust.length <= 104
trust[i].length == 2
All the pairs of trust are unique.
ai != bi
1 <= ai, bi <= n
 */
public class FindTownJudge {

    public static void main(String[] args) {
        int inhabitants = 3;
        int[][] trust = {{1,3},{2,3}};

        int townJudge = findJudge(inhabitants, trust);
        System.out.println(townJudge);

        int inhabitants1 = 2;
        int[][] trust1 = {{1,2}};

        int townJudge1 = findJudge(inhabitants1, trust1);
        System.out.println(townJudge1);

        int inhabitants2 = 3;
        int[][] trust2 = {{1,3},{2,3},{3,1}};

        int townJudge2 = findJudge(inhabitants2, trust2);
        System.out.println(townJudge2);
    }

    private static int findJudge(int n, int[][] trust) {

      int[] trustsCount = new int[n + 1];//no zero index in our case

      for (int[] trustRelation : trust) {

          int whoTrusts = trustRelation[0];
          int whoDoesItTrust = trustRelation[1];

          trustsCount[whoTrusts]--;//decrement if this person trusts someone
          trustsCount[whoDoesItTrust]++;//increment if this person is BEING trusted by someone

      }

     for(int i = 1 ; i <= n; i++){//from 1 as our trustCount array ignored zero index

         if(trustsCount[i] == n - 1){// == n -1 means this person is BEING trusted by ALL others
             return i  ;
         }

     }

     return -1;
    }
}
