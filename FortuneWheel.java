package main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class FortuneWheel {

	
	
	public static void run() {
		runNEWSim();
	}
	
	public static double sum(double[] p) {
		double tot = 0;
		for (int i = 0; i < p.length; i++) {
			tot += p[i];
		}
		return tot;
	}
	
	public static double min(double[] p) {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < p.length; i++) {
			if (p[i] < min) {
				min = p[i];
			}
		}
		return min;
	}
	
	public static void printArray(double[] array) {
		DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8); // Set as needed for desired decimal places
        System.out.println();
		System.out.print('[');
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				System.out.print(df.format(array[i]) + "]\n");
			} else {
				System.out.print(df.format(array[i]) + ", ");
			}
		}
	}
	
	public static void printArray(BigDecimal[] array) {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8); // Set as needed for desired decimal places

        System.out.println();
        System.out.print('[');
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print(df.format(array[i]) + "]\n");
            } else {
                System.out.print(df.format(array[i]) + ", ");
            }
        }
    }
	
	// Helper method to find minimum value in BigDecimal array
    private static BigDecimal min(BigDecimal[] array) {
        BigDecimal min = array[0];
        for (BigDecimal value : array) {
            if (value.compareTo(min) < 0) {
                min = value;
            }
        }
        return min;
    }
    
    // Helper method to sum elements of BigDecimal array
    private static BigDecimal sum(BigDecimal[] array) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal value : array) {
            sum = sum.add(value);
        }
        return sum;
    }
	
//	public static double[] calculateOdds(double[] payouts) {
//		
//		// define initial guesses for O
//		double[] O = new double[payouts.length];
//		for (int i = 0; i < payouts.length; i++) {
//			O[i] = 1 / payouts[i];
//		}
//		
//		// determine scalar
//		double scaleFactor = 1 / min(O);
//		
//		// normalize O with scalar
//		for (int i = 0; i < payouts.length; i++) {
//			O[i] *= scaleFactor * 100_000;
//		}
//		
//		double totalPositions = sum(O);
//		double tolerance = 0.001;
//		long maxIterations = 10_000_000_000l;
//		long iterations = 0l;
//		
//		boolean improved = true;
//		
//		while (improved && iterations < maxIterations) {
//			improved = false;
//			totalPositions = sum(O);
//			
//			for (int i = 0; i < payouts.length; i++) {
//				
//				if (Math.abs((payouts[i] * O[i]) - totalPositions) < tolerance) {
//					continue;
//				}
//				
//				
//				if (payouts[i] * (O[i] + 1) <= totalPositions + tolerance) {
//					O[i]++;
//					improved = true;
//				} else if (payouts[i] * O[i] > totalPositions + tolerance) {
//					O[i]--;
//					improved = true;
//				}
//				
//			}
//			iterations += 1l;
//			if (iterations == maxIterations) {
//				System.out.println("WARNING: Max Iterations Reached Without Full Convergence\n");
//			}
//		}
//		
//		return O;
//	}
//	
	public static BigDecimal[] calculateOdds(double[] payouts) {
        // Initialize BigDecimal array for O with initial guesses inversely proportional to payouts
        BigDecimal[] O = new BigDecimal[payouts.length];
//        for (int i = 0; i < payouts.length; i++) {
//            O[i] = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(payouts[i]), 10, RoundingMode.HALF_UP);
//        }
//        
//        // Determine the scale factor
//        BigDecimal scaleFactor = BigDecimal.ONE.divide(min(O), 10, RoundingMode.HALF_UP);
//
//        // Normalize O with scale factor and scale up
//        for (int i = 0; i < payouts.length; i++) {
//            O[i] = O[i].multiply(scaleFactor).multiply(BigDecimal.valueOf(100)).setScale(10, RoundingMode.HALF_UP);
//        }
//        System.out.printf("Scale Factor: %.4f", scaleFactor);
//        printArray(O);
//        System.out.println();

        BigDecimal N = BigDecimal.ONE;
        for (int i = 0; i < payouts.length; i++) {
        	N = N.multiply(BigDecimal.valueOf(payouts[i]));
        }
        
        for (int i = 0; i < payouts.length; i++) {
        	O[i] = N.divide(BigDecimal.valueOf(payouts[i]), 10, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(1_000));
        }
        
        BigDecimal totalPositions = sum(O);
        BigDecimal tolerance = new BigDecimal("1"); // Adjust tolerance as needed
        int maxIterations = 10_000_000;
        int iterations = 0;
        
        boolean improved = true;
        
        while (improved && iterations < maxIterations) {
            improved = false;
            totalPositions = sum(O);
            
            for (int i = 0; i < payouts.length; i++) {
                BigDecimal currentValue = BigDecimal.valueOf(payouts[i]).multiply(O[i]);
                
//                if (currentValue.subtract(totalPositions).abs().compareTo(tolerance) < 0) {
//                    continue;
//                }
                
                // Try incrementing if within tolerance bounds
                if (BigDecimal.valueOf(payouts[i]).multiply(O[i].add(BigDecimal.ONE)).compareTo(totalPositions.add(tolerance)) <= 0) {
                    O[i] = O[i].add(BigDecimal.ONE);
//                    totalPositions = totalPositions.add(BigDecimal.ONE);
                    improved = true;
                }
                // Decrement slightly if it overshoots
                else if (currentValue.compareTo(totalPositions.add(tolerance)) > 0) {
                    O[i] = O[i].subtract(BigDecimal.ONE);
//                    totalPositions = totalPositions.subtract(BigDecimal.ONE);
                    improved = true;
                }
                // check if value is zero
                else if (O[i].equals(BigDecimal.ZERO)) {
                	O[i] = O[i].add(BigDecimal.ONE);
                	improved = true;
                }
            }
            iterations += 1;
            if (iterations == maxIterations) {
                System.out.println("WARNING: Max Iterations Reached Without Full Convergence\n");
            }
        }
        
        return O;
    }
   
	
	public static void runNEWSim() {
		System.out.println("Running New And Improved Fortune Wheel");
		
//		int[] payouts = {   2,        3 ,    5,     10,     25,     50   };
//		int[] odds    = { 230000,  158000, 95000,  48000,   20000,  10000 };
		
		int[] payouts = {    2,          3 ,        5,        10,         25,        40    };
		int[] odds    = { 160000000, 110000000,  70000000,  40000000,   16256684,  10160427 };
		
//		int[] payouts = {   2,      3,      5,     10,     25 };
//		int[] odds    = { 100012500, 100008333, 60612373,  30306186,  12122474};
		// find odds where odds[i] * ( payouts[i] + 1 ) < totalOdds
		
		boolean calcOdds = false;
		
		if (calcOdds) {
			System.out.println("Calculating Odds For Test Set Of Payouts\n");
			double[] testPayouts = { 2, 3, 5, 10, 25, 40 };
			BigDecimal[] O = calculateOdds(testPayouts);
			BigDecimal sumOfO = sum(O);
			
			DecimalFormat df = new DecimalFormat("#");
	        df.setMaximumFractionDigits(8); // Set as needed for desired decimal places
			
			System.out.println("PAYOUTS: ");
			printArray(testPayouts);
			System.out.println("\nODDS: ");
			printArray(O);
			System.out.println("\nSum Of Odds: " + df.format(sumOfO));
			
			BigDecimal[] ORatio = new BigDecimal[O.length];
			for (int i = 0; i < O.length; i++) {
				ORatio[i] = O[i]
		                   .multiply(BigDecimal.valueOf(testPayouts[i]))
		                   .divide(sumOfO, 1000, RoundingMode.HALF_UP); // Adjust scale (10) as needed
			}
			System.out.println("Ratio of Payouts To Sum");
			printArray(ORatio);
			return;
		} else {
			System.out.println("Running Simulation On Payout and Odds Data");
		}
		
		int[] totalOdds = new int[odds.length];
		for (int i = 0; i < odds.length; i++) {
			if (i == 0) {
				totalOdds[0] = odds[0];
			} else {
				totalOdds[i] = totalOdds[i - 1] + odds[i];
			}
//			System.out.printf("%d, ", totalOdds[i]);
		}
		
		int totalOptions = totalOdds[totalOdds.length - 1];
		System.out.printf("Total Options: %d\n",totalOptions);
		
		double startingValue = 0;
		double currentValue = startingValue;
		double bet = 1.0;
		
		// betting all on the end gives you a specific edge 2% (101 / 51)
		
		// we need to make sure that the ratio of ( payout * odds ) < totalOdds for each item
		// ideally we keep the ratio of ( payout * odds ) / totalOdds the same for each item as well
		// otherwise certain numbers will have a far worse house edge than others
		int[] guesses = { 1, 1, 1, 1, 1, 1 }; // { 0, 0, 0, 0, 0, 1 };
		
		double oddsOfZero = 0;//5; // 5%
		
		double totalBet = 0;
		for (int i = 0; i < guesses.length; i++) {
			totalBet += guesses[i] * bet;
		}
		
		int iterations = 25_000_000;
		
		Random r = new Random();
		
		double[] currentValuess = new double[guesses.length];
		double currentValuessTwo = 0;
		for (int currentGuessNo = 0; currentGuessNo < guesses.length; currentGuessNo++) {
			for (int i = 0; i < iterations; i++) {
				
				currentValuess[currentGuessNo] -= bet;
				currentValuessTwo -= totalBet;
				
				if (r.nextInt(100) < oddsOfZero) {
					continue;
				}
				
				int index = r.nextInt(Integer.MAX_VALUE) % totalOptions;
				int winner = totalOdds.length;
				
				for (int j = 0; j < totalOdds.length; j++) {
					if (index < totalOdds[j]) {
						winner = j;
						break;
					}
				}
				
				double payout = (double)payouts[winner] * bet;
				if (currentGuessNo == winner) {
					currentValuess[currentGuessNo] += payout;
				}
				currentValuessTwo += payout * guesses[winner];
			}
		}
		
		System.out.printf("\nIndividual Guesses House Edges:\n");
		for (int i = 0; i < guesses.length; i++) {
			double houseEdge = 100 * ( ( -1 * currentValuess[i] ) / ( bet * (double)iterations));
			System.out.printf("%d: %.4f%%\n", payouts[i], houseEdge);
		}
		
		System.out.printf("\n");
		double guessOnAllEdge = 100 * ( ( -1 * currentValuessTwo ) / ( bet * (double)iterations * guesses.length));
		System.out.printf("Guessing 1 On All: %.4f%%\n", guessOnAllEdge);
		
		
		

//		for (int i = 0; i < iterations; i++) {
//			
//			currentValue -= totalBet;
//			
//			int index = r.nextInt(Integer.MAX_VALUE) % totalOptions;
//			int winner = totalOdds.length;
//			
//			for (int j = 0; j < totalOdds.length; j++) {
//				if (index < totalOdds[j]) {
//					winner = j;
//					break;
//				}
//			}
//			
//			double payout = (double)payouts[winner] * guesses[winner] * bet;
//			currentValue += payout;
//			
//		}
		
//		double userEdge = 100 * ( currentValue / ( totalBet * (double)iterations));
//		double houseEdge = 100 * ( ( -1 * currentValue ) / ( totalBet * (double)iterations));
//		
//		System.out.printf("User Value: %.1f\n", currentValue);
//		System.out.printf("House Edge: %.4f%%\n", houseEdge);
		
	}
	
	
	
	
	
	
	public static void runOLDSim() {
		
		double buyIn = 1;
		
		double[] prizes = {
			2,   5,   0,  25,  8,    100,   0,   333,   0,   50,   25,   0,   50,   2.5,   0,   10,   5,   0,    2.5,  4,  0.5
		};
		
		int[] prizeWeights = {
			50, 30,  350, 15,  25,    4,   750,   1,   735,   8,   15,  500,   8,   40,   300,  15,  30,  350,   40,  30,  200
		};
		
		double totalWon = 0;
		double totalSpent = 0;
		
		int numGamesAboveBuyIn = 0;
		double totalProduct = 0;
		
		int totalWeight = 0;
		for (int i = 0; i < prizeWeights.length; i++) {
			totalWeight += prizeWeights[i];
		}
		
		for (int i = 0; i < prizes.length; i++) {
//			totalSpent += buyIn;
//			totalWon += prizes[i];
			
			totalProduct += ( prizes[i] * prizeWeights[i] ) / totalWeight;
		}
		
//		System.out.printf("Total Spent %.2f\n", totalSpent);
//		System.out.printf("Total Won %.2f\n", totalWon);
		
		int prizeWeightTotal = 0;
		int totalOverBuyIn = 0;
		int[] prizeWeightTotals = new int[prizeWeights.length];
		for (int i = 0; i < prizeWeights.length; i++) {
			prizeWeightTotal += prizeWeights[i];
			prizeWeightTotals[i] = prizeWeightTotal;
			System.out.print(prizeWeightTotals[i] + " ");
			if (prizes[i] > buyIn) {
				totalOverBuyIn += prizeWeights[i];
			}
		}
		System.out.println("\nprizeWeightTotal: " + prizeWeightTotal + "\n");
		
		int runs = 50_000_000;
		double startingValue = 0;
		double houseValue = 0;
		double houseValueNoFee = 0;
		double totalFees = 0;
		double fee = 0.01;
		
		double feeAmount = buyIn * fee;
		totalFees += feeAmount;
		
		double amountForHouse = buyIn - feeAmount;
		Random r = new Random();
		for (int i = 0; i < runs; i++) {
			startingValue -= buyIn;
			houseValueNoFee += buyIn;
			houseValue += amountForHouse;
			
			int rng = r.nextInt(Integer.MAX_VALUE) % prizeWeightTotal;
			int option = prizeWeights.length;
			for (int j = 0; j < prizeWeights.length; j++) {
				if (rng < prizeWeightTotals[j]) {
					option = j;
					break;
				}
			}
			if (option >= prizeWeights.length) {
				System.out.println("Error finding match!!!! " + rng);
			}
			
			double prize = prizes[option];
			if (prize > buyIn) {
				numGamesAboveBuyIn++;
			}
			totalSpent += buyIn;
			totalWon += prize;
			
			houseValue -= prize;
			houseValueNoFee -= prize;
			startingValue += prize;
		}
		System.out.printf("Total Spent %.2f\n", totalSpent);
		System.out.printf("Total Won %.2f\n", totalWon);
		System.out.printf("Player Value %.2f\n", startingValue);
		System.out.printf("Total Product %.4f\n", totalProduct);
		System.out.printf("House Edge: %.4f%%\n", 100 * ( houseValue / ( runs * buyIn ) ) );
		System.out.printf("House Edge (No Fee): %.4f%%\n", 100 * ( houseValueNoFee / ( runs * buyIn ) ) );
		System.out.printf("Number of games above $%.0f: %d (%.4f%%)\n", buyIn, numGamesAboveBuyIn, 100*((double)numGamesAboveBuyIn / runs));
		System.out.printf("Weight Above $%.0f: %d / %d", buyIn, totalOverBuyIn, prizeWeightTotal);
	}
	
}
