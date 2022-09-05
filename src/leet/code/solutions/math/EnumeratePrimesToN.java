package leet.code.solutions.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumeratePrimesToN {

    public static void main(String[] args) {

    }

    private static List<Integer> primes(int n) {

        List<Integer> res = new ArrayList<>();

        //divide and concuer - pre-fill all NON primes when found prime and set it's multiplications to FALSE straight away
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));//initially all true - devivde and conquer will set falses as we go
        isPrime.add(0, false);//zero and one are NOT primes by design
        isPrime.add(1, false);

        for (int p = 2; p <= n; p++) {
            if (isPrime.get(p)) {//found in boolean array ? so if isPrime == true
                res.add(p);
            }

            //anyways multiplications of current number means that its division is not itself only ( 2,4,6,8 .... or 3,6,9) - so NON prime anyways
            for (int j = p; j <= n; j = j + p) {//from this number - to it's multiplications ( j = j + p )
                isPrime.set(j, false);
            }
        }
        return res;

    }
}
