package pl.jrobertgardzinski.primes;

import pl.jrobertgardzinski.primes.generator.KnuthAlgorithm;
import pl.jrobertgardzinski.primes.generator.PrimesGenerator;
import pl.jrobertgardzinski.primes.printer.ConsolePrinter;
import pl.jrobertgardzinski.primes.printer.PrimesPrinter;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        PrimesGenerator primesGenerator = new KnuthAlgorithm(1000);
        PrimesPrinter primesPrinter = new ConsolePrinter.Builder(50, 4).columnMinimumSpace(16).build();

        List<Integer> primes = primesGenerator.generate();

        primesPrinter.print(primes);
    }
}
