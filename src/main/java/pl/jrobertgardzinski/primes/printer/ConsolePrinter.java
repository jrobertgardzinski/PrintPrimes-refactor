package pl.jrobertgardzinski.primes.printer;

public class ConsolePrinter implements PrimesPrinter {

    @Override
    public void print(int[] primes) {
        final int M = 1000; // primes array length
        final int RR = 50; // rows?
        final int CC = 4; // columns?

        int P[] = primes; // primes
        int PAGENUMBER;
        int PAGEOFFSET;
        int ROWOFFSET;
        int C;

        PAGENUMBER = 1;
        PAGEOFFSET = 1;
        while (PAGEOFFSET <= M) {

            // header section
            System.out.println("The First " + M + " Prime Numbers --- Page " + PAGENUMBER);
            System.out.println("");

            // prime numbers
            for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++) {
                for (C = 0; C < CC; C++)
                    if (ROWOFFSET + C * RR <= M)
                        System.out.format("%10d", P[ROWOFFSET + C * RR]);
                System.out.println("");
            }

            // page ending
            System.out.println("\f");
            PAGENUMBER = PAGENUMBER + 1;
            PAGEOFFSET = PAGEOFFSET + RR * CC;
        }
    }
}
