package pl.jrobertgardzinski.primes.generator;

public class KnuthAlgorithm implements PrimesGenerator {

    private final int M; // primes array length
    private final int ORDMAX; // for M == 1000 MULT length will never exceed 30

    int P[]; // primes
    int J; // next odd number
    int K; // index of primes
    boolean JPRIME; // while loop condition
    int ORD; // order?
    int SQUARE; // disclaimer: odd^2 == odd
    int N;
    int MULT[]; // list of composite numbers

    public KnuthAlgorithm(int m, int ORDMAX) {
        this.M = m;
        this.ORDMAX = ORDMAX;

        P = new int[M + 1];
        MULT = new int[ORDMAX + 1];
    }

    public int[] generate() {
        setupValues();

        while (arrayOfPrimesIsNotFilled()) {
            calculate();
        }

        return P;
    }

    public int[] getPrimes(){
        return P;
    }

    private void setupValues() {
        J = 1;
        K = 1;
        P[1] = 2;
        ORD = 2;
        SQUARE = 9;
    }

    private boolean arrayOfPrimesIsNotFilled() {
        return K < M;
    }

    private void calculate() {
        do {
            getNextOddNumber();
            updateSquareArray();
            seekForPrime();
        } while (primeNotFound());
        doSomeStuffWithFoundPrimeNumber();
    }

    private void getNextOddNumber() {
        J = J + 2;
    }

    private void updateSquareArray() {
        if (J == SQUARE) {
            ORD = ORD + 1;
            SQUARE = P[ORD] * P[ORD]; //9, 25, 49 etc.
            MULT[ORD - 1] = J; // 9, 25, 49, etc.
        }
    }

    private void seekForPrime() {
        // finding prime number
        N = 2;
        JPRIME = true;
        while (N < ORD && JPRIME) {
            // myk z punktu 24. knuth literate programming
            // let's say - modified sieve of Erastothenes
            while (MULT[N] < J)
                MULT[N] = MULT[N] + P[N] + P[N];
            // check if J is composite number
            if (MULT[N] == J)
                JPRIME = false;
            N = N + 1;
        }
    }

    private boolean primeNotFound() {
        return !JPRIME;
    }

    private void doSomeStuffWithFoundPrimeNumber() {
        K = K + 1;
        P[K] = J;
    }

}
