package main;

public class OptimalPlinko {
	
	public static double[] halfBuckets3 = {
			1000.0,
			250.0,
			100,
			50,
			10,
			5.1,
			2.7,
			1.3,
			0.5,
			0.25
	};
	
	public static void run() {
		
//		double[] payouts = {5, 0.5, 2.5};
//		double[] payouts = {25, 10, 4.5, 2.2, 1.2, 0.6, 0.33, 0.6, 1.2, 2.2, 4.5, 10, 25}; // Modest
//		double[] payouts = { 1000.0, 250.0, 100, 50, 10, 5.1, 2.7, 1.3, 0.5, 0.25, 0.1, 0.25, 0.5, 1.3, 2.7, 5.1, 10, 50, 100, 250, 1000.0 }; // Total Degen
		double[] modestPayouts = {25, 10, 4.5, 2.2, 1.2, 0.6, 0.33, 0.6, 1.2, 2.2, 4.5, 10, 25 }; // Modest
		double[] averagePayouts = {50.0, 25.0, 10.0, 5.5, 3.0, 1.25, 0.5, 0.3, 0.5, 1.25, 3.0, 5.5, 10.0, 25.0, 50.0 }; // Average
		double[] daredevilPayouts = {100.0, 33.0, 17.0, 8.5, 4.4, 2.6, 1.25, 0.75, 0.5, 0.25, 0.5, 0.75, 1.25, 2.6, 4.4, 8.5, 17.0, 33.0, 100.0 }; // Daredevil
		double[] totalDegenPayouts = { 1000.0, 250.0, 100, 50, 25, 10, 4.8, 2.2, 0.5, 0.25, 0.1, 0.25, 0.5, 2.2, 4.8, 10, 25, 50, 100, 250, 1000.0 }; // Total Degen
		
		double[] totalDegenPayoutsNEW = { 1000.0, 250.0, 100, 55, 19, 9.5, 4.2, 1.9, 0.5, 0.25, 0.1, 0.25, 0.5, 1.9, 4.2, 9.5, 19, 55, 100, 250, 1000.0 }; // Total Degen
		
		// ODDS OUTPUT:
		double[] modestOdds = { 100, 930, 3326, 8288, 16555, 34854, 64852 };
		double[] averageOdds = { 100, 932, 5660, 13365, 28305, 75120, 196055, 330525 };
		double[] daredevilOdds = { 100, 5411, 19982, 55902, 126787, 230156, 504529, 857372, 1298600, 2622521 };
		double[] totalDegenOdds = { 100, 300, 643, 1211, 2345, 5747, 11889, 25848, 113468, 226859, 567033 };
		
		double[] totalDegenOddsNEW = { 100, 316, 660, 1125, 3080, 6067, 13604, 29958, 113579, 227065, 567523  };
		
		double[] payouts = totalDegenPayoutsNEW;
		
		double[] functionOdds = calculateProbabilities(payouts);
		int[] inverseOdds = turnOddsNumeric(functionOdds);
		double mean = determineMean(payouts, functionOdds);
		
		System.out.println("\nPayouts:");
		printArray(payouts, 1);
		System.out.println("\nOdds:");
		printArray(functionOdds, 5);
		System.out.println("\nNumeric Odds:");
		printArray(inverseOdds, 1);
		printSum("Sum Of Numeric Odds:", inverseOdds);
		
		printSum("Sum(P):",functionOdds);
		System.out.printf("Mean: %.3f", mean);

	}
	
	public static String getPrecision(int precision) {
		return "%." + precision + "f, ";
	}
	
	public static void printArray(double[] arr, int precision) {
		
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.printf(getPrecision(precision), arr[i]);
		}
		System.out.print("]\n");
	}
	
	public static void printArray(int[] arr, int precision) {
		
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%d, ", arr[i]);
		}
		System.out.print("]\n");
	}
		
	// determines the `mean` (Mu) or the RTP value for the given set of payouts and probabilities
	public static double determineMean(double[] payouts, double[] probabilities) {
		double mu = 0;
		for (int i = 0; i < payouts.length; i++) {
			mu += ( payouts[i] * probabilities[i] );
		}
		return mu;
	}
	
	public static int[] turnOddsNumeric(double[] arr) {
		int[] P = new int[arr.length];
		double scalar = 100.0 / arr[0];
		for (int i = 0; i < arr.length; i++) {
			P[i] = (int)( scalar * arr[i] );
		}
		return P;
	}
	
	public static double printSum(String startStr, double[] p) {
		double tot = 0;
		for (int i = 0; i < p.length; i++) {
			tot += p[i];
		}
		System.out.printf("\n%s %.3f\n", startStr, tot);
		return tot;
	}
	
	public static double printSum(String startStr, int[] p) {
		int tot = 0;
		for (int i = 0; i < p.length; i++) {
			tot += p[i];
		}
		System.out.printf("\n%s %d\n", startStr, tot);
		return tot;
	}
	
	public static double[] calculateProbabilities(double[] X) {
        int n = X.length; // Number of elements in X
        double[] P = new double[n];       // Final array to hold probabilities
        double sumP, mean;
        double tolerance = 0.0001;        // Tolerance level for the convergence
        double adjustmentFactor = 0.01;   // Adjustment factor for iterative refinement
        int maxIterations = 10000;        // Limit the number of iterations
        int iteration = 0;

        // Step 1: Initialize probabilities based on the inverse of X[i]
        double sumPPrime = 0;
        for (int i = 0; i < n; i++) {
            P[i] = 1.0 / X[i];  // Initial guess using inverse of X[i]
            sumPPrime += P[i];  // Sum of the unscaled probabilities
        }

        // Normalize the initial guess so that the sum of probabilities is 1
        for (int i = 0; i < n; i++) {
            P[i] /= sumPPrime;  // Normalize probabilities
        }

        // Step 2: Iteratively adjust probabilities to satisfy both constraints
        while (iteration < maxIterations) {
            iteration++;
            // Calculate the current mean and sum of probabilities
            mean = 0;
            sumP = 0;
            for (int i = 0; i < n; i++) {
                mean += X[i] * P[i];  // Calculate the mean
                sumP += P[i];         // Sum of probabilities (should be 1)
            }

            // Check if both conditions (mean = 1 and sum = 1) are satisfied
            if (Math.abs(mean - 1) < tolerance && Math.abs(sumP - 1) < tolerance) {
                break;  // If both conditions are satisfied within tolerance, stop
            }

            // Step 3: Adjust probabilities iteratively based on mean error
            double meanDifference = mean - 1;  // How far we are from the desired mean

            // Adjust each probability based on its contribution to the mean
            for (int i = 0; i < n; i++) {
                P[i] -= adjustmentFactor * meanDifference * (P[i] * X[i]);  // Adjust based on mean difference and contribution to mean
            }

            // Re-normalize the probabilities so that their sum equals 1
            double newSumP = 0;
            for (int i = 0; i < n; i++) {
                newSumP += P[i];
            }
            for (int i = 0; i < n; i++) {
                P[i] /= newSumP;  // Re-normalize to sum to 1
            }
        }

        if (iteration == maxIterations) {
            System.out.println("Warning: Maximum iterations reached without full convergence.");
        } else {
        	System.out.println("Success: System Found A Viable Solution With " + iteration + " Attempts");
        }

        return P;  // Return the final array of probabilities
    }
	
//	public static double[] calculateProbabilities(double[] X) {
//        int n = X.length; // Number of elements in X
//        double[] P_prime = new double[n]; // Array to hold unscaled probabilities
//        double[] P = new double[n];       // Final array to hold scaled probabilities
//        double sumPPrime = 0;             // To hold the sum of unscaled probabilities
//        double sumWeightedPPrime = 0;     // To hold the sum of weighted unscaled probabilities
//
//        // Step 1: Calculate unscaled probabilities P_prime based on the inverse of X[i]
//        for (int i = 0; i < n; i++) {
//            P_prime[i] = 1.0 / X[i];  // Unscaled probability
//            sumPPrime += P_prime[i];  // Sum of unscaled probabilities
//            sumWeightedPPrime += P_prime[i] * X[i];  // Sum of weighted unscaled probabilities
//        }
//
//        // Step 2: Calculate the scaling factor to ensure the mean is 1
//        double scalingFactor = sumWeightedPPrime; // Scaling factor to ensure mean = 1
//
//        // Step 3: Normalize the probabilities so that they sum to 1
//        for (int i = 0; i < n; i++) {
//            P[i] = P_prime[i] / sumPPrime;  // Scale probabilities so they sum to 1
//        }
//
//        return P; // Return the final array of probabilities
//    }
	
//	public static double[] calculateProbabilities(double[] X) {
//        double[] P = new double[X.length]; // Initialize the probability array
//        
//        // Step 1: Set up variables for probabilities
//        double sumX = 0;
//        for (int i = 0; i < X.length; i++) {
//            sumX += 1.0 / X[i]; // Sum the reciprocal of each element in X
//        }
////        System.out.println("\nSumX: " + sumX);
//
//        // Step 2: Calculate each probability P[i] using the formula P[i] = (1 / X[i]) / sumX
//        for (int i = 0; i < X.length; i++) {
//            P[i] = (1.0 / X[i]) / sumX;
//        }
//
//        return P; // Return the array of probabilities
//    }
	
}
