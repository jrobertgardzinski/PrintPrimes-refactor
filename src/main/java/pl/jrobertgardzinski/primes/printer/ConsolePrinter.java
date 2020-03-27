package pl.jrobertgardzinski.primes.printer;

import java.util.List;

public class ConsolePrinter implements PrimesPrinter {
    private final int RR; // rows?
    private final int CC; // columns?
    private final int columnMinimumSpace;

    private List<Integer> primes;
    private int PAGENUMBER;
    private int PAGEOFFSET;

    private ConsolePrinter(Builder builder) {
        this.RR = builder.RR;
        this.CC = builder.CC;
        this.columnMinimumSpace = builder.columnMinimumSpace;
    }

    @Override
    // TODO think about some validation logic before printing primes
    public void print(List<Integer> primes) {
        this.primes = primes;

        resetPageData();
        while (areAllPrimesPrinted()) {
            printNewPage();
        }
    }

    private void resetPageData() {
        PAGENUMBER = 1;
        PAGEOFFSET = 1;
    }

    private boolean areAllPrimesPrinted() {
        return PAGEOFFSET < primes.size();
    }

    private void printNewPage() {
        printHeader();
        printPrimes();
        finishPrintingPage();
    }

    private void printHeader() {
        System.out.println("The First " + primes.size() + " Prime Numbers --- Page " + PAGENUMBER);
        System.out.println("");
    }

    private void printPrimes() {
        for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++) {
            for (int C = 0; C < CC; C++)
                if (ROWOFFSET + C * RR <= primes.size())
                    System.out.format("%10d", primes.get(ROWOFFSET + C * RR));
            System.out.println("");
        }
    }

    private void finishPrintingPage() {
        System.out.println("\f");
        PAGENUMBER = PAGENUMBER + 1;
        PAGEOFFSET = PAGEOFFSET + RR * CC;
    }

    public static class Builder {
        private final int RR;
        private final int CC;
        private int columnMinimumSpace;

        public Builder(int rr, int cc) {
            this.RR = rr;
            this.CC = cc;
            // initialize optional variable
            this.columnMinimumSpace = 6;
        }

        public Builder columnMinimumSpace(int columnMinimumSpace) {
            this.columnMinimumSpace = columnMinimumSpace;
            return this;
        }

        public ConsolePrinter build() {
            ConsolePrinter consolePrinter = new ConsolePrinter(this);
            if (consolePrinter.columnMinimumSpace <= 0) {
                throw new IllegalStateException("columnMinimumSpace must be greater than 0");
            }
            return consolePrinter;
        }
    }
}
