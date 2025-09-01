package leet.code.solutions.graphs.union_find;

import java.util.*;

/*
721

https://leetcode.com/problems/accounts-merge/description/

Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:

Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

Constraints:

1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
 */
public class AccountsMerge {

        private static int[] parent;

        private static List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();

            // Initialize Union-Find: each account is its own parent initially
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            // Map: email -> first account index that contains this email
            Map<String, Integer> emailToFirstAccount = new HashMap<>();

            // Process each account and union accounts with shared emails
            for (int i = 0; i < n; i++) {
                List<String> account = accounts.get(i);

                // Skip name (index 0), process emails (index 1+)
                for (int j = 1; j < account.size(); j++) {
                    String email = account.get(j);

                    if (emailToFirstAccount.containsKey(email)) {
                        // Email exists in another account -> union them
                        int firstAccount = emailToFirstAccount.get(email);

                        parent[find(i)] = find(firstAccount);
                    } else {
                        // First time seeing this email -> remember which account has it
                        emailToFirstAccount.put(email, i);
                    }
                }
            }

            // Group emails by their root parent account
            Map<Integer, Set<String>> rootToEmails = new HashMap<>();
            for (int i = 0; i < n; i++) {
                List<String> account = accounts.get(i);

                // Add all emails from account i to its root's email set
                for (int j = 1; j < account.size(); j++) {
                    String email = account.get(j);
                    int root = find(i);
                    rootToEmails.computeIfAbsent(root, k -> new HashSet<>()).add(email);
                }
            }

            // Build final result
            List<List<String>> result = new ArrayList<>();
            for (Map.Entry<Integer, Set<String>> entry : rootToEmails.entrySet()) {
                int rootAccount = entry.getKey();
                Set<String> emails = entry.getValue();

                // Create merged account: [name, sorted emails...]
                List<String> mergedAccount = new ArrayList<>(emails);
                Collections.sort(mergedAccount);  // Sort emails
                mergedAccount.add(0, accounts.get(rootAccount).get(0));  // Add name at front

                result.add(mergedAccount);
            }

            return result;
        }

        // Find with path compression
        private static int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        // Test the solution
        public static void main(String[] args) {

            List<List<String>> accounts = Arrays.asList(
                    Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                    Arrays.asList("Mary", "mary@mail.com"),
                    Arrays.asList("John", "johnnybravo@mail.com")
            );

            System.out.println("Input: " + accounts);
            System.out.println("\nOutput: " + accountsMerge(accounts));
        }
    }
// #hard