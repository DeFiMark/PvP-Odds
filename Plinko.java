package main;

import java.util.Arrays;
import java.util.Random;

public class Plinko {
	
	
//	public static double[] totalDegenPayouts = { 1000.0, 250.0, 100, 50, 25, 10, 4.8, 2.2, 0.5, 0.25 }; // Total Degen
////	public static int[] totalDegenWeights = {100, 300, 643, 1211, 2345, 5747, 11889, 25848, 113468, 226859, 567033}; // GPT WEIGHTS
//	public static int[] totalDegenWeights = {10,  310, 725, 1400, 2400, 6200, 13000, 28000, 113000, 200000, 500000}; // MY ADJUSTED WEIGHTS
	
//	public static double[] halfBuckets3 = {
//			1000.0,
//			250.0,
//			100,
//			50,
//			10,
//			5.1,
//			2.7,
//			1.3,
//			0.5,
//			0.25
//	}; // 11 , 13 , 17 , 21
//	public static int[] halfBucketWeights3 = {
//			1,      // 1000
//			25,     // 250.0
//			410,    // 100
//			760,    // 50.0
//			3000,   // 10
//			6500,   // 5.1
//			13000,  // 2.7
//			17000,  // 1.3
//			30000,  // 0.5
//			40000,  // 0.25
//			275200, // 0.1
//	};
	
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
	}; // 11 , 13 , 17 , 21
	public static int[] halfBucketWeights3 = {
			1,      // 1000
			25,     // 250.0
			430,    // 100
			800,    // 50.0
			2402,   // 10
			3500,   // 5.1
			6400,   // 2.7
			12000,  // 1.3
			24000,  // 0.5
			32000,  // 0.25
			235092, // 0.1
	};
//	public static int[] halfBucketWeights3GPT = { 100, 395, 738, 1304, 5851, 11314, 21225, 43906, 113891, 227618, 568799 };
	public static int[] halfBucketWeights3GPT = { 80, 430, 750, 1325, 5860, 11320, 21225, 43910, 113900, 227650, 568800 };

	public static double[] halfBuckets2 = {
			100.0,
			33.0,
			17.0,
			8.5,
			4.4,
			2.6,
			1.25,
			0.75,
			0.5,
	};
	public static int[] halfBucketWeights2 = { // 0.58192%
			7,     // 100.0
			25,    // 33.0
			53,    // 17.0
			150,   // 8.5
			300,   // 4.4
			350,   // 2.6
			400,   // 1.25
			1266,  // 0.75
			2400,  // 0.5
			10000, // 0.25
	};
	
	
	/** Second Safest Game Mode */
//	public static double[] halfBuckets1 = {
//			21,
//			5.4,
//			3.7,
//			2.0,
//			1.1,
//			0.7
//	};
//	public static int[] halfBucketWeights1 = {
//			4,     // 21
//			110,   // 5.4
//			210,   // 3.7
//			500,   // 2.0
//			900,   // 1.1
//			2000,  // 0.7
//			3984   // 0.4
//	};
	
	public static double[] halfBuckets1 = {
			50.0,
			25.0,
			10.0,
			5.5,
			3.0,
			1.25,
			0.5
	};
	public static int[] halfBucketWeights1 = {
			1,     // 50.0
			20,    // 25.0
			80,    // 10.0
			215,   // 5.5
			420,   // 3.0
			700,   // 1.25
			2500,  // 0.5
			5900   // 0.3
	};
	
//	public static double[] halfBuckets1 = {
//			10.0,
//			3.8,
//			2.2,
//			1.5,
//			0.6,
//			0.4,
//			0.2
//	};
//	public static int[] halfBucketWeights1 = {
//			14,   // 10.0
//			60,   // 3.8
//			100,  // 2.2
//			160,  // 1.5
//			200,  // 0.6
//			200,  // 0.4
//			200,  // 0.2
//			331,  // 0.1
//	};
	
	
//	public static double[] halfBuckets0 = {
//		8,
//		3,
//		2,
//		1.1,
//		0.8
//	};
//	public static int[] halfBucketWeights0 = {
//		4,    // 8
//		37,   // 3
//		60,   // 2
//		100,  // 1.1
//		215,  // 0.8
//		552,  // 0.5
//	};

	public static double[] halfBuckets0 = {
			25,
			10,
			4.5,
			2.2,
			1.2,
			0.6
		};
		public static int[] halfBucketWeights0 = {
			1,     // 25.0
			18,    // 10.0
			50,    // 4.5
			100,   // 2.2
			160,   // 1.2
			400,   // 0.6
			1100,  // 0.33
		};
	
	public static int[] halfBucketWeights0GPT = {
			100,     // 25.0
			930,    // 10.0
			3326,    // 4.5
			8288,   // 2.2
			16555,   // 1.2
			34854,   // 0.6
			64852,  // 0.33
		};

	public static double MODEST_CENTER = 0.33;
	public static double AVERAGE_CENTER = 0.3;
	public static double DAREDEVIL_CENTER = 0.2;
	public static double TOTALDEGEN_CENTER = 0.1;
	
	// PAYOUTS
	public static double[] modestPayouts = {25, 10, 4.5, 2.2, 1.2, 0.6 }; // Modest
	public static double[] averagePayouts = {50.0, 25.0, 10.0, 5.5, 3.0, 1.25, 0.5 }; // Average
	public static double[] daredevilPayouts = {100.0, 33.0, 17.0, 8.5, 4.4, 2.6, 1.25, 0.75, 0.5 }; // Daredevil
	public static double[] totalDegenPayoutsNEW = { 1000.0, 250.0, 100, 55, 19, 9.5, 4.2, 1.9, 0.5, 0.25 }; // Total Degen
	
	
//	public static double[] totalDegenPayouts = { 1000.0, 250.0, 100, 50, 25, 10, 4.8, 2.2, 0.5, 0.25 }; // Total Degen

	// ODDS OUTPUT: (GPT)
//	public static int[] modestOdds = { 100, 930, 3326, 8288, 16555, 34854, 64852 };
//	public static int[] averageOdds = { 100, 932, 5660, 13365, 28305, 75120, 196055, 330525 };
//	public static int[] daredevilOdds = { 100, 5411, 19982, 55902, 126787, 230156, 504529, 857372, 1298600, 2622521 };
//	public static int[] totalDegenOdds = { 100, 300, 643, 1211, 2345, 5747, 11889, 25848, 113468, 226859, 567033 };
	
	// ODDS OUTPUT: MY CHANGES
	public static int[] modestOdds = { 2, 5, 11, 25, 75, 138, 380 };
	public static int[] averageOdds = { 122, 350, 1000, 2500, 5000, 12000, 28000, 100000 };
	public static int[] daredevilOdds = { 25, 50, 100, 200, 500, 1200, 2500, 4000, 5500, 20000 };
//	public static int[] totalDegenOdds = { 40, 300, 700, 1350, 2500, 5800, 13000, 26000, 110000, 200000, 546000 };
	
	public static int[] totalDegenOddsNEW = { 30, 300, 800, 1400, 3400, 6400, 15000, 30000, 110000, 220000, 528500  };

	public static final int nBallsToDrop = 5_000_000;
	public static final int simulation_runs = 1; // 100;
	
	public static void run() {
//		runSim(halfBuckets0, halfBucketWeights0, nBallsToDrop, simulation_runs, 0, CENTER_BUCKET0, false);
//		runSim(halfBuckets0, halfBucketWeights0GPT, nBallsToDrop, simulation_runs, 0, CENTER_BUCKET0, false);
//		runSim(halfBuckets1, halfBucketWeights1, nBallsToDrop, simulation_runs, 1, CENTER_BUCKET1, true);
////		calculatePercentages(halfBuckets1, halfBucketWeights1, CENTER_BUCKET1);
//		runSim(halfBuckets2, halfBucketWeights2, nBallsToDrop, simulation_runs, 2, CENTER_BUCKET2, true);
//		runSim(halfBuckets3, halfBucketWeights3, nBallsToDrop, simulation_runs, 3, CENTER_BUCKET3, false);
//		runSim(halfBuckets3, halfBucketWeights3GPT, nBallsToDrop, simulation_runs, 3, CENTER_BUCKET3, false);
		
		
		runSim(modestPayouts, modestOdds, nBallsToDrop, simulation_runs, 3, MODEST_CENTER, false, 100, 0.51);
		runSim(averagePayouts, averageOdds, nBallsToDrop, simulation_runs, 3, AVERAGE_CENTER, false, 100, 0.485);
		runSim(daredevilPayouts, daredevilOdds, nBallsToDrop, simulation_runs, 3, DAREDEVIL_CENTER, false, 100, 0.525);
		runSim(totalDegenPayoutsNEW, totalDegenOddsNEW, nBallsToDrop, simulation_runs, 3, TOTALDEGEN_CENTER, false, 100, 0.385);
//		
//		runTest();
		
		if (true) {
			return;
		}
		
		double[] payouts = {5.0, 0.5, 5.0}; // Example payout values
        double[] probabilities = calculateProbabilities(payouts);
        
        if (probabilities != null) {
            System.out.println("Probabilities: ");
            for (double probability : probabilities) {
                System.out.printf("%.5f ", probability);
            }
        } else {
            System.out.println("No valid probabilities found.");
        }
		
		
        
//		double[] payouts = {5.0, 0.5, 0.25, 0.5, 5.0};
        double[] odds = calculateOdds(payouts);
        System.out.println("Odds: " + Arrays.toString(odds));
        System.out.println("Sum: " + sum(odds));
        
        double totalProduct = 0.0;
        for (int i = 0; i < payouts.length; i++) {
        	totalProduct += ( odds[i] * payouts[i] );
        }
        System.out.println("Total Product: " + totalProduct + "\n");
        
//        double productDiscrepancy = totalProduct - 1;
//        if (productDiscrepancy < 0) {
//        	System.out.println("Negative Product " + productDiscrepancy);
//        	return;
//        }
        
        for (int i = 0; i < odds.length; i++) {
        	odds[i] /= totalProduct;
        }
        
        System.out.println("New Odds: " + Arrays.toString(odds));
        System.out.println("Sum: " + sum(odds));
        
        double newTotalProduct = 0.0;
        for (int i = 0; i < payouts.length; i++) {
        	newTotalProduct += ( odds[i] * payouts[i] );
        }
        System.out.println("New Total Product: " + newTotalProduct + "\n");
        
        double sumProd = sum(odds);
        for (int i = 0; i < odds.length; i++) {
        	odds[i] /= sumProd;
        }
        System.out.println("New Odds: " + Arrays.toString(odds));
        System.out.println("Sum: " + sum(odds));
        
        double lastTotalProduct = 0.0;
        for (int i = 0; i < payouts.length; i++) {
        	lastTotalProduct += ( odds[i] * payouts[i] );
        }
        System.out.println("Last Total Product: " + lastTotalProduct + "\n");
//        System.out.println("\nCalculating For Integers:\n");
//        
//        double[] fullInts = new double[odds.length];
//        
//        for (int i = 0; i < odds.length; i++) {
//        	fullInts[i] = odds[i] * 1_000;
//        }
//        System.out.println(Arrays.toString(fullInts));
	}
	
	public static double[] calculateProbabilities(double[] payouts) {
        if (payouts.length != 3) {
            System.out.println("This method is designed for exactly three payouts.");
            return null;
        }

        double p0 = payouts[0];
        double p1 = payouts[1];
        double p2 = payouts[2];
        
        double[] probabilities = new double[3];
        
        // Solving the linear system:
        // z0 * p0 + z1 * p1 + z2 * p2 = 1
        // z0 + z1 + z2 = 1
        // Express z2 = 1 - z0 - z1
        // Substitute z2 in the first equation:
        // z0 * p0 + z1 * p1 + (1 - z0 - z1) * p2 = 1
        // Simplify to find z0 and z1:
        // z0 * (p0 - p2) + z1 * (p1 - p2) = 1 - p2
        
        // Rearrange to find z1 in terms of z0:
        // z1 = (1 - p2 - z0 * (p0 - p2)) / (p1 - p2)
        
        // Since z0 + z1 + z2 = 1
        // z0 is free variable, we need to check if there's a valid solution:
        double A = p0 - p2;
        double B = p1 - p2;
        double C = 1 - p2;
        
        // Assume z0 = 0 for simplicity, calculate z1 and z2:
        probabilities[0] = 0; // start with z0 = 0
        probabilities[1] = C / B;
        probabilities[2] = 1 - probabilities[0] - probabilities[1];
        
        // Check if all probabilities are valid (0 <= probability <= 1)
        for (double probability : probabilities) {
            if (probability < 0 || probability > 1) {
                return null; // If any probability is invalid, return null
            }
        }

        return probabilities;
    }
	
	public static double sum(double[] arr) {
		double total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		return total;
	}
	
	public static double[] calculateOdds(double[] payouts) {
        int n = payouts.length;
        double[] odds = new double[n];
        double sumReciprocals = 0.0;

        // Calculate the sum of the reciprocals of the payouts
        for (int i = 0; i < n; i++) {
            sumReciprocals += 1.0 / payouts[i];
        }

        // Calculate the odds for each bucket
        for (int i = 0; i < n; i++) {
            odds[i] = (1.0 / payouts[i]) / sumReciprocals;
        }

        return odds;
    }
	
	public static void runTest() {
		
//		double[] odds = { 2, 8, 2 };
//		double[] payouts = { 2, 0.5, 2 };
		double[] odds = { 40, 400, 800, 400, 40 };
		double[] payouts = {5.0, 0.5, 0.25, 0.5, 5.0};
		double[] summedOdds = new double[odds.length];
		
		int totalOdds = 0;
		for (int i = 0; i < odds.length; i++) {
			totalOdds += odds[i];
			summedOdds[i] = totalOdds;
		}
		
		double totalProduct = 0;
		for (int i = 0; i < odds.length; i++) {
			double specificOdds = odds[i] / totalOdds;
			totalProduct += specificOdds * payouts[i];
		}
		
		System.out.println("Total Odds: " + totalOdds);
		System.out.println("Total Product: " + totalProduct);
		int runs = 10_000_000;
		double value = 0;
		Random r = new Random();
		for (int i = 0; i < runs; i++) {
			int rng = r.nextInt(Integer.MAX_VALUE);
			int pos = rng % totalOdds;
			int bucket = summedOdds.length;
			for (int j = 0; j < summedOdds.length; j++) {
				if (pos < summedOdds[j]) {
					bucket = j;
					break;
				}
			}
			
			if (bucket >= summedOdds.length) {
				System.out.println("ERROR, BUCKET EQUALS BUCKET TOTAL!!!!");
				return;
			}
			
			value -= 1;
			value += payouts[bucket];
		}
		System.out.println("Value: " + value);
	}
	
	public static void calculatePercentages(double[] halfBuckets, int[] halfBucketWeights, double CENTER_BUCKET) {
		
		double[] buckets = new double[( halfBuckets.length * 2 ) + 1];
		int[] bucketWeights = new int[buckets.length];
		
		for (int i = 0; i < buckets.length; i++) {
			if (i == halfBuckets.length) {
				buckets[i] = CENTER_BUCKET;
				bucketWeights[i] = halfBucketWeights[halfBucketWeights.length - 1];
			} else {
				buckets[i] = i > halfBuckets.length ? halfBuckets[ ( 2 * halfBuckets.length ) - i  ] : halfBuckets[i];
				bucketWeights[i] = i > halfBuckets.length ? halfBucketWeights[ ( 2 * halfBucketWeights.length ) - ( i + 2 ) ] : halfBucketWeights[i];
			}
		}
		
		double total = 0;
		double[] odds = new double[buckets.length];
		for (int i = 0; i < buckets.length; i++) {
			double odd = 1.0 / buckets[i];
			total += odd;
			odds[i] = odd;
		}
		
		double totalWeightPoints = 100_000;
		double totalCount = 0;
		for (int i = 0; i < buckets.length; i++) {
			int count =	(int) ((int)( totalWeightPoints * odds[i]) / total);
			totalCount += count;
			System.out.printf("%.2f:   %.5f%% |  %.3f / %.3f  |   %d\n", buckets[i], 100 * ( odds[i] / total) , odds[i], total, count);
			
		}
		System.out.println("Total Count: " + totalCount);
		
	}
	
	public static void runSim(
			double[] halfBuckets, 
			int[] halfBucketWeights, 
			int numBalls, 
			int simRuns, 
			int riskLevel, 
			double CENTER_BUCKET, 
			boolean fetchingInputsOnly, 
			int boostOdds, 
			double payoutReduction
	) {
		
		double bet = 1;
		double reducedBet = bet * payoutReduction;
		
		double[] buckets = new double[( halfBuckets.length * 2 ) + 1];
		int[] bucketWeights = new int[buckets.length];
		
		for (int i = 0; i < buckets.length; i++) {
			if (i == halfBuckets.length) {
				buckets[i] = CENTER_BUCKET;
				bucketWeights[i] = halfBucketWeights[halfBucketWeights.length - 1];
			} else {
				buckets[i] = i > halfBuckets.length ? halfBuckets[ ( 2 * halfBuckets.length ) - i  ] : halfBuckets[i];
				bucketWeights[i] = i > halfBuckets.length ? halfBucketWeights[ ( 2 * halfBucketWeights.length ) - ( i + 2 ) ] : halfBucketWeights[i];
			}
		}
		
		String arrayName = riskLevel == 0 ? "weightsForZero" : riskLevel == 1 ? "weightsForOne" : riskLevel == 2 ? "weightsForTwo" : "weightsForThree";
		double startingHouseValue = 100;
		
		double betterValue = 0;
		double houseValue = startingHouseValue;
		double houseValueNoFee = startingHouseValue;
		double totalFees = 0;
		
		double fee = 0.01;
		
		double fees = bet * fee;
		double amountForHouse = bet - fees;
		
		int[] bucketWins = new int[buckets.length];
		
		Random r = new Random();
		
		int[] bucketTotals = new int[bucketWeights.length];
		
		System.out.print("[");
		for (int i = 0; i < bucketWeights.length; i++) {
			if (i == 0) {
				bucketTotals[i] = bucketWeights[i];
			} else {
				bucketTotals[i] = bucketWeights[i] + bucketTotals[i - 1];
			}
			System.out.printf(bucketTotals[i] + ",");
		}
		System.out.print("]\n[");
		int topBucket = bucketTotals[bucketTotals.length - 1];
		
//		System.out.printf("Running %d Run Simulation With %d Buckets For Risk Level %d\n\n\n", numBalls, buckets.length, riskLevel);
//		for (int i = 0; i < bucketWeights.length; i++) {
//			System.out.println(arrayName + "[" + i + "] = " + bucketTotals[i] + ";");
//		}
		for (int i = 0; i < bucketWeights.length; i++) {
//			System.out.println("gameModes[" + riskLevel + "].payouts[" + i + "] = " + (int)(buckets[i] * 10_000) + ";");
			System.out.printf("%d,", (int)(buckets[i] * 10_000));
		}
		System.out.print("]\n[");
		for (int i = 0; i < bucketWeights.length; i++) {
//			System.out.println("gameModes[" + riskLevel + "].payouts[" + i + "] = " + (int)(buckets[i] * 10_000) + ";");
			System.out.printf("%d,", i);
		}
		System.out.print("]\n\n\n");
//		System.out.println("\nBuckets And Weights:");
		
		if (fetchingInputsOnly) {
			return;
		}
		
		double[] bucketOdds = new double[bucketWeights.length];
		double totalProduct = 0;
		for (int i = 0; i < bucketWeights.length; i++) {
			bucketOdds[i] = (double)bucketWeights[i] / topBucket;
//			System.out.println(buckets[i] + ": " + bucketWeights[i] + " / " + topBucket);
			totalProduct += ( bucketOdds[i] * buckets[i] );
		}
		System.out.println();
		double[] betterValues = new double[simRuns];
		
		for (int a = 0; a < simRuns; a++) {
			betterValue = 0;
			for (int i = 0; i < numBalls; i++) {
				
				betterValue -= bet;
				houseValue += amountForHouse;
				houseValueNoFee += bet;
				totalFees += fees;
				
				int rng = r.nextInt(Integer.MAX_VALUE);
				
				int pos = rng % topBucket;
				int bucket = bucketTotals.length;
				for (int j = 0; j < bucketTotals.length; j++) {
					if (pos < bucketTotals[j]) {
						bucket = j;
						break;
					}
				}
				
				if (r.nextInt(100) < boostOdds) {
					if (bucket != 0 && bucket != bucketTotals.length - 1) {
						if (bucket == halfBuckets.length) {
							bucket = r.nextInt(2) == 0 ? bucket - 1 : bucket + 1;
						} else if (bucket < halfBuckets.length) {
							bucket--;
						} else {
							bucket++;
						}
					}
				}
				
				if (bucket >= bucketTotals.length) {
					System.out.println("ERROR, BUCKET EQUALS BUCKET TOTAL!!!!");
					return;
				}
				
				bucketWins[bucket]++;
				
				double payout = ( reducedBet * buckets[bucket] );
				
				betterValue += payout;
				houseValue -= payout;
				houseValueNoFee -= payout;
			}
			
			betterValues[a] = betterValue;
			
		}
		
		double oddsOverOne = 0;
		
		System.out.println("\n\nBucket Wins:");
		for (int i = 0; i < bucketWins.length; i++) {
			System.out.printf(
				buckets[i] + ":   %d   (%.4f%%  =>  %.4f%%)\n", 
				bucketWins[i], 
				( 100 * (double)bucketWins[i] / ( numBalls * simRuns ) ),
				100 * (double)bucketWeights[i] / topBucket
			);              
			double val = 100 * (double)bucketWeights[i] / topBucket;
			if (buckets[i] > 1) {
				oddsOverOne += val;
			}
		}
		
		System.out.println("\n\nCombined Liklihood");
		for (int i = 0; i < halfBucketWeights.length - 1; i++) {
			System.out.printf(
				buckets[i] + ": %.6f%%\n", 
				200 * (double)halfBucketWeights[i] / topBucket
			);              
		}
		System.out.printf(
				CENTER_BUCKET + ": %.4f%%\n", 
				100 * (double)halfBucketWeights[halfBucketWeights.length - 1] / topBucket
			);   
		System.out.printf("Wins Over 1: %.3f%%\n", oddsOverOne);
		
		if (boostOdds > 0) {
			System.out.println("\n\nBoost Increase");
			for (int i = 0; i < halfBucketWeights.length - 1; i++) {
				System.out.printf(
					buckets[i] + ": %.4f%%  |  %.4f%%\n", 
					200 * (double)halfBucketWeights[i] / topBucket, // take ratio of wins to expected wins
					200 * ((double)bucketWins[i] / ( numBalls * simRuns ) )
				);              
			}
			System.out.printf(
				CENTER_BUCKET + ": %.4f%%  |  %.4f%%\n", 
				100 * (double)halfBucketWeights[halfBucketWeights.length - 1] / topBucket,
				200 * ((double)bucketWins[halfBucketWeights.length - 1] / ( numBalls * simRuns ) )
			);   
		}
		
		
//		System.out.printf("\nTotal Fees %d\n", (int)totalFees);
//		System.out.printf("\nPlatform Fee %.3f%%\n", 100 * fee);
//		System.out.printf("Total Bucket: %d\n\n", topBucket);
		
//		System.out.printf("Better Profit: %.2f\n", betterValue);
//		System.out.printf("House Profit: %.2f\n", ( houseValue - startingHouseValue ));
		
		System.out.printf("\nTotal Product: %.8f\nCalculated House Edge: %.6f%%\nCalc House Edge (w/ Fee): %.6f%%\n\n", totalProduct, 100 * ( 1 - totalProduct ), 100 * ( ( 1 - totalProduct ) - fee) );

		System.out.printf("House Edge: %.5f%%\n", ( 100 * ( houseValue - startingHouseValue ) / ( bet * numBalls )));
//		System.out.printf("House Edge (No Fee): %.5f%%\n\n\n", ( 100 * ( houseValueNoFee - startingHouseValue ) / ( bet * numBalls )));
		
		if (simRuns > 1) {
			System.out.printf("returns on %d games of %d balls worth %.2f matic\n[", simRuns, numBalls, bet);
			double total = 0;
			for (int i = 0; i < simRuns; i++) {
				String start = betterValues[i] < 0 ? "" : "+";
				System.out.printf("%s%.3f, ", start, betterValues[i]);
				total += betterValues[i];
			}
			System.out.printf("]\nTotal: %.3f\n", total);
		}
		
	}
	
}
