package pl.jrobertgardzinski.primes.generator;

public class KnuthAlgorithm implements PrimesGenerator {

    private final int quantityOfPrimes;

    int primes[];
    int nextOddNumber;
    int currentPrimesIndex;
    boolean isPrimeComputed;
    int order; // Not sure if name is accurate
    int square;
    int multiplicitiesOfCompositeNumbers[];

    public KnuthAlgorithm(int quantityOfPrimes) {
        this.quantityOfPrimes = quantityOfPrimes;

        primes = new int[this.quantityOfPrimes + 1];
        multiplicitiesOfCompositeNumbers = new int[calculateLengthOfMultiplicitiesOfCompositeNumbers()];
    }

    private int calculateLengthOfMultiplicitiesOfCompositeNumbers() {
        return (int)Math.floor(Math.sqrt(quantityOfPrimes)) + 1;
    }

    public int[] generate() {
        setupValues();

        while (arrayOfPrimesIsNotFilled()) {
            calculate();
        }

        return primes;
    }

    public int[] getPrimes(){
        return primes;
    }

    private void setupValues() {
        nextOddNumber = 1;
        currentPrimesIndex = 1;
        primes[1] = 2;
        order = 2;
        square = 9;
    }

    private boolean arrayOfPrimesIsNotFilled() {
        return currentPrimesIndex < quantityOfPrimes;
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
        nextOddNumber = nextOddNumber + 2;
    }

    private void updateSquareArray() {
        if (nextOddNumber == square) {
            order = order + 1;
            square = primes[order] * primes[order];
            multiplicitiesOfCompositeNumbers[order - 1] = nextOddNumber;
        }
    }

    private void seekForPrime() {
        int N = 2;
        isPrimeComputed = true;
        while (N < order && isPrimeComputed) {
            // 24. knuth literate programming
            // let's say - modified sieve of Erastothenes
            while (multiplicitiesOfCompositeNumbers[N] < nextOddNumber)
                multiplicitiesOfCompositeNumbers[N] = multiplicitiesOfCompositeNumbers[N] + primes[N] + primes[N];
            // check if J is composite number
            if (multiplicitiesOfCompositeNumbers[N] == nextOddNumber)
                isPrimeComputed = false;
            N = N + 1;
        }
    }

    private boolean primeNotFound() {
        return !isPrimeComputed;
    }

    private void doSomeStuffWithFoundPrimeNumber() {
        currentPrimesIndex = currentPrimesIndex + 1;
        primes[currentPrimesIndex] = nextOddNumber;
    }

}
