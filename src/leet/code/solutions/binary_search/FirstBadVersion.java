package leet.code.solutions.binary_search;

import java.util.Random;

/*
https://leetcode.com/problems/first-bad-version/

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check.
Since each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which returns whether version is bad.
Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example 1:
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.

Example 2:
Input: n = 1, bad = 1
Output: 1

Constraints:
1 <= bad <= n <= 231 - 1
Accepted
964,616
Submissions

 */
public class FirstBadVersion {

    public static void main(String[] args) {
        int firstBAd = firstBadVersion(6);
        System.out.println(firstBAd);
    }

    public static int firstBadVersion(int n) {
        int left = 0;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {//still bad at mid

                right = mid;//reduce right to mid

            } else {//not bad - but we have to continue checking to find LAST bad version
                left = mid + 1;
            }

            //as we ended above with left = right
            if (left == right && isBadVersion(left)) {//either left or right ( when they are equal == ) are bad version
                return left;//does not matter left OR right here
            }
        }

        return -1;//not found

    }

    private static boolean isBadVersion(int mid) {

        return new Random().nextBoolean();//mock for here
    }
}
