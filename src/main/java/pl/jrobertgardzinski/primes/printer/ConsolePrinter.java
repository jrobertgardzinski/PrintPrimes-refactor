package pl.jrobertgardzinski.primes.printer;

import java.util.List;

public class ConsolePrinter implements PrimesPrinter {
    private final int rows;
    private final int columns;
    private final int columnMinimumSpace;
    private String cellTemplate;

    private List<Integer> primes;
    // TODO consider merging pageNumber and pageOffset into one class
    private int pageNumber;
    private int pageOffset;

    private ConsolePrinter(Builder builder) {
        this.rows = builder.rows;
        this.columns = builder.columns;
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
        pageNumber = 1;
        pageOffset = 1;
        cellTemplate = createCellTemplate();
    }

    private String createCellTemplate() {
        Integer lastPrime = primes.get(primes.size()-1);
        Integer lastPrimeStringLength = lastPrime.toString().length();
        Integer cellWidth = lastPrimeStringLength + columnMinimumSpace;
        return "%" + cellWidth + "d";
    }

    private boolean areAllPrimesPrinted() {
        return pageOffset < primes.size();
    }

    private void printNewPage() {
        printHeader();
        printPrimes();
        finishPrintingPage();
    }

    private void printHeader() {
        System.out.println("The First " + primes.size() + " Prime Numbers --- Page " + pageNumber);
        System.out.println("");
    }

    private void printPrimes() {
        for (int rowOffset = pageOffset; rowOffset < pageOffset + rows; rowOffset++) {
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                if (rowOffset + currentColumn * rows <= primes.size()) {
                    System.out.format(cellTemplate, primes.get(rowOffset + currentColumn * rows));
                }
            }
            System.out.println("");
        }
    }

    private void finishPrintingPage() {
        System.out.println("\f");
        pageNumber = pageNumber + 1;
        pageOffset = pageOffset + rows * columns;
    }

    public static class Builder {
        private final int rows;
        private final int columns;
        private int columnMinimumSpace;

        public Builder(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
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
