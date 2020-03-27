package pl.jrobertgardzinski.primes;

import pl.jrobertgardzinski.primes.generator.KnuthAlgorithm;
import pl.jrobertgardzinski.primes.generator.PrimesGenerator;
import pl.jrobertgardzinski.primes.printer.ConsolePrinter;
import pl.jrobertgardzinski.primes.printer.PrimesPrinter;

import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        PrimesGenerator pg = new KnuthAlgorithm();
        PrimesPrinter pp = new ConsolePrinter();

        int[] primes = pg.generate();

        pp.print(primes);
    }
}
