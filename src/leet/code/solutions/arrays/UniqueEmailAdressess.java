package leet.code.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/unique-email-addresses/description/

very valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.

For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.

For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.

For example, "m.y+name@email.com" will be forwarded to "my@email.com".
It is possible to use both of these rules at the same time.

Given an array of strings emails where we send one email to each emails[i], return the number of different addresses that actually receive mails.



Example 1:

Input: emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.
Example 2:

Input: emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
Output: 3

 */
public class UniqueEmailAdressess {
    public static void main(String[] args) {
     String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
      int unques = numUniqueEmails(emails);
        System.out.println(unques);
    }

    private static int numUniqueEmails(String[] emails) {
        Set<String > unqueEmails = new HashSet<>();

        for (String email : emails) {
            StringBuilder address = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {

                char currChar = email.charAt(i);

                if(currChar == '.') {
                       continue;
                } else if (currChar == '+') {

                    while(email.charAt(i) != '@'){//skip all after + untill @
                        i++;
                    }

                    address.append(email.substring(i + 1));//substring exluding skipped

                } else {
                    address.append(currChar);
                }

            }
            unqueEmails.add(address.toString());
        }
        return unqueEmails.size();
    }
}
