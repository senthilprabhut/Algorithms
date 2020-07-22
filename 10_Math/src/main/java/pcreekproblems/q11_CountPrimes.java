package pcreekproblems;


import java.util.Arrays;

/**
 * Problem:
 *  Count the number of prime numbers LESS than a non-negative number, n.
 *
 * Approaches:
 *  Approach 1: Let's start with a isPrime function. To determine if a number is prime, we need to check if it is not divisible by any number less than n.
 *  The runtime complexity of isPrime function would be O(n) and hence counting the total prime numbers up to n would be O(n2)
 *
 *  Approach 2: As we know the number must not be divisible by any number > n / 2,
 *  we can immediately cut the total iterations half by dividing only up to n / 2 but runtime is still O(n2)
 *
 *  Approach 3: Let's write down all of 12's factors. As you can see, calculations of 4 × 3 and 6 × 2 are not necessary.
 *  Therefore, we only need to consider factors up to √n because, if n is divisible by some number p,
 *  then n = p × q and since p ≤ q, we could derive that p ≤ √n.
 *  Our total runtime has now improved to O(n1.5), which is slightly better.
 *
 *  Approach 4: The Sieve of Eratosthenes is a pseudo-polynomial algorithm since it has an exponential time complexity with regard to input size
 *
 * Links:
 *  https://leetcode.com/problems/count-primes/description/
 *  https://www.programcreek.com/2014/04/leetcode-count-primes-java/
 */
public class q11_CountPrimes {
    public static void main(String[] args) {
        q11_CountPrimes cp = new q11_CountPrimes();
        System.out.printf("The prime count less than %d is %d%n", 55, cp.countPrimes1(55));
        System.out.printf("The prime count less than %d is %d%n", 55, cp.countPrimes2(55));
    }

    /**
     * Approach:
     *  The Sieve of Eratosthenes is one of the most efficient ways. We start off with a table of n numbers.
     *  Let's look at the first number, 2. We know all multiples of 2 must not be primes, so we mark them off as non-primes.
     *  Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 × 2 = 6, 3 × 3 = 9, ... must not be primes,
     *  so we mark them off as well. Since 4 is not a prime, it is divisible by 2 and so we can skip 4 immediately
     *  And the loop has to go till sqrt(n) numbers
     *
     * Links:
     *  https://www.programcreek.com/2014/04/leetcode-count-primes-java/
     *
     * Complexity:
     *  Time Complexity is O(n log log n)
     *  Space Complexity is O(n)
     */
    public int countPrimes1(int n) {
        if(n<=2) return 0; //there are no primes less than 2

        int primeCount = 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for(int i=2; i<=Math.sqrt(n); i++) {
            if(!isPrime[i]) continue;

            //i is a prime number - mark its multiples as non-prime
            int cntr=2;
            while ( (i*cntr) < n) {
                isPrime[i*cntr] = false;
                cntr++;
            }
        }

        for(int i=2; i<n; i++) {
            if(isPrime[i]) {
                primeCount++;
                System.out.printf("%d \t", i);
            }
        }
        System.out.println();
        return primeCount;
    }

    /**
     * Approach:
     *  Optimized version of the above algorithm
     *  It inverts the true / false meanings in the traditional Sieve of Eratosthenes implementation.
     *  true, here, means a composite number, not a prime.
     *  It doesn't update the array values for any even numbers.
     *  They all stay false, because changing them to true would be needless bookkeeping.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/35033/12-ms-java-solution-modified-from-the-hint-method-beats-99-95/10
     */
    public int countPrimes2(int n) {
        //if n = 2, the prime 2 is not less than n, so there are no primes less than n
        if(n<=2) return 0;

        /*
         * Start with the assumption that half the numbers below n are prime candidates, since we know that half of them
         * are even, and so _in general_ aren't prime. An exception to this is 2, which is the only even prime.
         * But also 1 is an odd which isn't prime. These two exceptions (a prime even and a for-sure not-prime odd)
         * cancel each other out for n > 2, so our assumption holds.
         * We'll decrement count when we find an odd which isn't prime.
         *  If n = 3,  c = 1.
         *  If n = 5,  c = 2.
         *  If n = 10, c = 5.
         */
        int primeCount = n/2;

        /**
         * Java initializes boolean arrays to {false}. In this method, we'll use truth to mark _composite_ numbers.
         * This is the opposite of most Sieve of Eratosthenes methods, which use truth to mark _prime_ numbers.
         * We will _NOT_ mark evens as composite, even though they are. This is because `c` is current after each `i` iteration below.
         */
        boolean[] isComposite = new boolean[n];

        //Starting with an odd prime-candidate above 2, increment by two to skip evens (which we know are not prime candidates).
        for (int i = 3; i * i < n; i += 2) {
            if (isComposite[i]) {
                //counter has already been decremented for this composite odd
                continue;
            }

            /*
             * For each prime i, iterate through the odd composites we know we can form from i, and mark them as composite
             * if not already marked.
             *    We know that i * i is composite. We also know that i * i + i is composite, since they share a common factor of i.
             *    Thus, we also know that i * i + a*i is composite for all real a, since they share a common factor of i.
             *
             *    Note, though, that i * i + i _must_ be composite for an independent reason: it must be even.
             *    (all i are odd, thus all i*i are odd, thus all (odd + odd) are even).
             *
             *    Recall that, by initializing c to n/2, we already accounted for all of the evens less than n being
             *    composite, and so marking i * i + (odd)*i as composite is needless bookkeeping.
             *
             *    So, we can skip checking i * i + a*i for all odd a, and just increment j by even multiples of i,
             *    since all (odd + even) are odd.
             */
            //We start from i*i instead of 2*i coz 2*i is even, and any no less than i is multiplied with i in its iteration
            for(int j=i*i; j<n; j += 2*i) {
                if(!isComposite[j]) {
                    isComposite[j] = true;
                    primeCount--;
                }
            }
        }
        return primeCount;
    }
}
