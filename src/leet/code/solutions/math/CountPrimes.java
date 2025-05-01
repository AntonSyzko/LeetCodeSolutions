package leet.code.solutions.math;

public class CountPrimes {

    public static void main(String[] args) {
        // Test cases
        System.out.println("Number of primes less than 10: " + countPrimes(10)); // Should output 4
        System.out.println("Number of primes less than 0: " + countPrimes(0));   // Should output 0
        System.out.println("Number of primes less than 1: " + countPrimes(1));   // Should output 0
        System.out.println("Number of primes less than 100: " + countPrimes(100)); // Should output 25
    }

    /**
     * Counts the number of prime numbers less than n using Sieve of Eratosthenes
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     */
    public static int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        // Create a boolean array "isPrime[0..n-1]" and initialize
        // all entries as true. A value in isPrime[i] will finally be
        // false if i is not a prime, else true.
        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        // Sieve of Eratosthenes algorithm
        // Only need to check up to sqrt(n)
        for (int i = 2; i * i < n; i++) {
            // If prime[i] is not changed, then it is a prime
            if (isPrime[i]) {
                // Update all multiples of i greater than or equal to i²
                // Numbers that are less than i² would have already been marked by smaller primes
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Count prime numbers
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }


}
