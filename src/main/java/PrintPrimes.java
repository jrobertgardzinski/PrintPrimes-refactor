public class PrintPrimes {
	public static void main(String[] args) {
		
		// input arguments
		final int M = 1000; // primes array length
		final int RR = 50; // rows?
		final int CC = 4; // columns?
		final int WW = 10; // cell characters width?
		final int ORDMAX = 30; // for M == 1000 MULT length will never exceed 30
		
		// instance variables
		int P[] = new int[M + 1]; // primes
		int PAGENUMBER;
		int PAGEOFFSET;
		int ROWOFFSET;
		int C;
		int J; // next odd number
		int K; // index of primes
		boolean JPRIME; // while loop condition
		int ORD; // order?
		int SQUARE; // disclaimer: odd^2 == odd
		int N;
		int MULT[] = new int[ORDMAX + 1]; // list of composite numbers
		
		// --- GENERATING PRIMES SECTION
		J = 1; 
		K = 1; 
		P[1] = 2;
		ORD = 2; 
		SQUARE = 9;
		
		while (K < M) {
			do {
				// get next odd number
				J = J + 2;
				
				// update square array MULT
				if (J == SQUARE) {
					ORD = ORD + 1;
					SQUARE = P[ORD] * P[ORD]; //9, 25, 49 etc.
					MULT[ORD - 1] = J; // 9, 25, 49, etc.
				}
				
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
			} while (!JPRIME);
			
			// prime number found - assigning
			K = K + 1;
			P[K] = J;
		}
		
		// --- PRINTING SECTION
		{
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
}