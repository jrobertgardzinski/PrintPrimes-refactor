package pl.jrobertgardzinski.primes.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimesGeneratorTest {
    private PrimesGenerator primesGenerator;
    private List<Integer> generatedPrimes;

    @BeforeAll
    void setUp() {
        int quantityOfPrimes = 1000;

        this.primesGenerator = new KnuthAlgorithm(quantityOfPrimes);

        generatedPrimes = primesGenerator.generate();
    }

    private List<Integer> convertToList(int[] primes) {
        return Arrays.stream(primes).boxed().collect(Collectors.toList());
    }

    @Test
    @DisplayName("Should contain some prime numbers")
    void containingPrimes() {
        List<Integer> primes = Stream.of(2, 3, 5, 7, 11, 13, 19, 2003, 3541)
                .collect(Collectors.toList());

        assertTrue(generatedPrimes.containsAll(primes));
    }

    @Test
    @DisplayName("Should not contain some composite numbers")
    void notContainingCompositeNumbers() {
        List<Integer> composites = Stream.of(4, 6, 8, 9, 12, 15, 18, 32, 64, 128, 256, 512, 1024)
                .collect(Collectors.toList());

        assertFalse(generatedPrimes.containsAll(composites));
    }
}