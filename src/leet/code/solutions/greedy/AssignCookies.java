package leet.code.solutions.greedy;

import java.util.Arrays;

/*
https://leetcode.com/problems/assign-cookies/description/

Assume you are an awesome parent and want to give your children some cookies.
 But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j].
If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content.
 Your goal is to maximize the number of your content children and output the maximum number.

Example 1:

Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
Example 2:

Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
You have 3 cookies and their sizes are big enough to gratify all of the children,
You need to output 2.

Constraints:

1 <= g.length <= 3 * 104
0 <= s.length <= 3 * 104
1 <= g[i], s[j] <= 231 - 1
 */
public class AssignCookies {

    public static void main(String[] args) {
        int [] childernGreed = {1,2,3};
        int[] cookies = {1,1};

        int contentChildren = findContentChildren(childernGreed, cookies);
        System.out.println(contentChildren);

        int [] childernGreed2 = {1,2};
        int[] cookies2 = {1,2,3};

        int contentChildren2 = findContentChildren(childernGreed2, cookies2);
        System.out.println(contentChildren2);

        int [] childernGreed3 = {1,2,3};
        int[] cookies3 = {3};

        int contentChildren3 = findContentChildren(childernGreed3, cookies3);
        System.out.println(contentChildren3);
    }

    private static int findContentChildren(int[] children, int[] cookies) {
        int contentChildren = 0;

        Arrays.sort(children);
        Arrays.sort(cookies);

        int greediestChildIndex = children.length -1;
        int largestCookieIndex = cookies.length -1;

        while(greediestChildIndex >= 0 && largestCookieIndex >= 0){

            int greediestChild = children[greediestChildIndex];
            int largestCookie = cookies[largestCookieIndex];

            if( largestCookie >= greediestChild){
                contentChildren++;
                greediestChildIndex--;
                largestCookieIndex--;
            } else {
                greediestChildIndex--;
            }
        }

        return contentChildren;
    }
}
