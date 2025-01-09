package main;
import java.util.Random;

public class SumProductSlots {
	
	// Somehow this below config equals exactly 0.00% house edge
//	public static double[] COINS_4_ROWS = {
//			500, 
//			400,
//			300, 
//			250,
//			200,
//			150, 
//			75,
//			50,
//			40,
//			30,
//			25,
//			20,
//			15,
//			10,
//			10,
//			10,
//			5,
//			4,
//			3,
//			2,
//			1
//	};


	
	public static int[] COINS_5_ROWS_DEGEN = { 
			500,
			400,
			300,
			300,
			250,
			250,
			250,
			200,
			200,
			200,
			200,
			150,
			100,
			75,
			75,
			50,
			50,
			50,
			50,
			25,
			25,
			25,
			25,
			25,
			20,
			20,
			20,
			20,
			20,
			20,
			10,
			10,
			10,
			10,
			10,
			10,
			10,
			10,
			5,
			1
		};
		
		public static int[] COINS_4_ROWS_DEGEN = {
				600,
				500,
				400,
				300,
				300,
				250,
				250,
				250,
				225,
				225,
				200,
				200,
				200,
				150,
				100,
				75,
				50,
				50,
				50,
				50,
				50,
				50,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				20,
				20,
				20,
				20,
				20,
				20,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				5,
				5,
				5,
				5,
				1,
		};
	
		public static int[] COINS_3_ROWS_DEGEN = {
				1000,
				500,
				400,
				300,
				300,
				250,
				250,
				250,
				200,
				200,
				200,
				200,
				150,
				100,
				50,
				50,
				50,
				50,
				50,
				50,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				5,
				1,
		};
		
		public static int[] COINS_3_ROWS_LOW = {
				400,
				250,
				225,
				200,
				200,
				175,
				150,
				125,
				100,
				75,
				75,
				75,
				50,
				50,
				50,
				50,
				50,
				50,
				50,
				25,
				25,
				25,
				25,
				25,
				25,
				25,
		};
		
		public static int[] COINS_4_ROWS_LOW = {
				300,
				250,
				225,
				200,
				200,
				200,
				175,
				150,
				125,
				100,
				75,
				50,
				50,
				50,
				50,
				50,
				50,
				25,
				25,
				25,
				25,
				25,
				20,
				20,
				20
		};
		
		public static int[] COINS_5_ROWS_LOW = {
				250,
				225,
				200,
				200,
				175,
				150,
				100,
				100,
				75,
				50,
				50,
				50,
				50,
				25,
				25,
				25,
				20,
				20
		};
		
		
		public static int[][][] SETUP = {
			{
				COINS_3_ROWS_LOW,
				COINS_4_ROWS_LOW,
				COINS_5_ROWS_LOW,
			},
			{
				COINS_3_ROWS_DEGEN,
				COINS_4_ROWS_DEGEN,
				COINS_5_ROWS_DEGEN
			}
		};
		
		public static int difficulty = 0; // 0 = low | 1 = high | 2 = degen
		public static int NUM_ROWS = 3;
		
		public static int[] COINS = SETUP[difficulty][NUM_ROWS - 3]; //COINS_3_ROWS_LOW;
		public static int NUM_COINS = COINS.length;
		

		public static boolean skipBreakdown = true;
		public static boolean just_print_contract_values = false;
		public static int runs = 50_000_000;
		
		public static int SPINS_PER_GAME = 20;
		public static int GAMES_TO_TRACK = 200;
		public static int betAmount = 1;
		
		public static int numCombinations() {
			return (int) Math.pow(NUM_COINS, NUM_ROWS);
		}
		
		public static double[] getPayouts() {
	        // Total number of combinations = NUM_COINS ^ NUM_ROWS
	        int totalCombinations = numCombinations();
	        double[] payouts = new double[totalCombinations];

	        for (int comboIndex = 0; comboIndex < totalCombinations; comboIndex++) {
	        	
	            // Decode comboIndex into NUM_ROWS indices
	            int[] indices = decodeCombination(comboIndex, NUM_COINS, NUM_ROWS);

	            // Compute payout for this combination
	            double payout = 1.0;
	            for (int idx : indices) {
	                payout *= ((double)COINS[idx] / 100.0);
	            }

	            payouts[comboIndex] = payout;
	        }

	        return payouts;
	    }

	    /**
	     * Decodes a single integer into an array of indices corresponding to a single combination.
	     * 
	     * @param comboIndex The combination index to decode (0-based)
	     * @param base The number of coins (NUM_COINS)
	     * @param length The number of rows (NUM_ROWS)
	     * @return An int array of length `length` where each element is an index in [0, base-1].
	     */
	    private static int[] decodeCombination(int comboIndex, int base, int length) {
	        int[] result = new int[length];
	        int value = comboIndex;

	        // The innermost dimension changes fastest, so we decode from last to first.
	        for (int pos = length - 1; pos >= 0; pos--) {
	            result[pos] = value % base;
	            value /= base;
	        }

	        return result;
	    }
	    
	    
	    public static double getRandomPayout(Random r) {
			double multiplier = 1;
			for (int i = 0; i < NUM_ROWS; i++) {
				int rng = r.nextInt(Integer.MAX_VALUE) % NUM_COINS;
				multiplier *= ( (double)COINS[rng] ) / 100.0;
			}
			
			return multiplier;
	    }
	    
	    
	    public static double[] runSim() {
	    	
			double startingValue = 0;
			double currentValue = startingValue;
			double houseValue = 0;
			double houseValueNoFees = 0;
			
			double fee = 0.02; // 2%
			double averagePayout_ = 0;

			int winsAboveOne = 0;
			int winsAboveTen = 0;
			
			
			double gameBuyIn = SPINS_PER_GAME * betAmount;
			double totalWonThisGame = 0;
			
			double[] gameOutputs = new double[GAMES_TO_TRACK];
			
			Random r = new Random();
			
			for (int i = 0; i < runs; i++) {
				if (i > 0 && i % SPINS_PER_GAME == 0) {
					int index = (i / SPINS_PER_GAME) - 1;
					if (index < GAMES_TO_TRACK) {
						gameOutputs[index] = totalWonThisGame - gameBuyIn;
						totalWonThisGame = 0;
					}
				}
				
				currentValue -= betAmount;
				
				double pFee = betAmount * fee;
				houseValue += (betAmount - pFee);
				houseValueNoFees += betAmount;
				
				double payoutMultiplier = getRandomPayout(r);
				
				if (payoutMultiplier >= 10) {
					winsAboveTen++;
				}
				if (payoutMultiplier >= 1) {
					winsAboveOne++;
				}

				double valueEarned = (betAmount * payoutMultiplier);
				currentValue += valueEarned;
				houseValue -= valueEarned;
				houseValueNoFees -= valueEarned;
				totalWonThisGame += valueEarned;
				averagePayout_ = ( ( averagePayout_ * ( (i+1) - 1 ) ) + ((double)payoutMultiplier) ) / (i+1);
				
			}
			
			
			System.out.printf("running simulation %d times\n", runs);
			System.out.printf("Average Payout: %.5fx\n", averagePayout_);
			System.out.printf("House Edge: %.4f%%\n", 100 * houseValue / (runs * betAmount));
			System.out.printf("House Edge (No Fees): %.4f%%\n", 100 * houseValueNoFees / (runs * betAmount));
			System.out.printf("Expected Return (User): %.4f%%\n", 100 * ( 1 + ( currentValue / ( runs * betAmount ))));
			System.out.printf("Real Platform Edge: %.4f%%\n", 100 - ( 100 * ( 1 + ( currentValue / ( runs * betAmount )))));
			System.out.printf("User Profit: %.2f\n\n", ( currentValue - startingValue ));
			System.out.printf("Wins Over 1x: %.3f%%\nWins Over 10x: %.3f%%\n", 100.0 * (double)winsAboveOne / runs, 100.0 * (double)winsAboveTen / runs);
			
			return gameOutputs;
	    }
	    
	    public static void runGameBreakdown(double[] gameOutputs) {
	    	System.out.printf("\n\n\nPrinting Output from %d Games for %d spins per game at %d per spin:\n\n", GAMES_TO_TRACK, SPINS_PER_GAME, (int)betAmount);
			System.out.println("Claim   |  Result   |  Percent Diff");
			double gameBuyIn = SPINS_PER_GAME * betAmount;
			int totWins = 0;
			int totLosses = 0;
			double biggestWin = 0;
			double biggestDiff = 0;
			double totalAmountAfter = 0;
			double averageWin = 0;
			double averageLoss = 0;
			for (int i = 0; i < GAMES_TO_TRACK; i++) {
				double claim = gameBuyIn + gameOutputs[i];
				double percentDiff = claim < gameBuyIn ? (-100 * (( gameBuyIn - claim ) / gameBuyIn)) : ( ( claim / gameBuyIn ) - 1 ) * 100;
				if (claim < gameBuyIn) {
					totLosses++;
					averageLoss += percentDiff;
				} else {
					totWins++;
					averageWin += percentDiff;
				}
				if (claim > biggestWin) {
					biggestWin = claim;
					biggestDiff = percentDiff;
				}
				totalAmountAfter += gameOutputs[i];
				String addPlus = claim < gameBuyIn ? "" : "+";
				System.out.printf("%.2f      %s%.2f        %s%.2f%%  \n", claim, addPlus, gameOutputs[i], addPlus, percentDiff);
			}
			
			System.out.printf("\n\nWins: %d\nLoss: %d\nBiggest Win: %.2f (+%.2f%%)\nTotal W/L: %.2f\n", totWins, totLosses, biggestWin, biggestDiff, totalAmountAfter);
			System.out.printf("Average Win:  +%.2f%%\nAverage Loss: %.2f%%\n", averageWin / totWins, averageLoss / totLosses);
	    }
		
		public static void run() {
			System.out.println("Running Slots");
			
			if (just_print_contract_values) {
				System.out.print("[");
				for (int i = 0; i < COINS.length; i++) {
					if (i < COINS.length - 1) {
						System.out.printf("%d,", COINS[COINS.length - (i + 1)]);
					} else {
						System.out.printf("%d]", COINS[0]);
					}
				}
				return;
			}
			
			int nCombinations = numCombinations();			
			double totalProduct = 0;
			
			double[] payouts = getPayouts();
			
			for (int i = 0; i < payouts.length; i++) {
				totalProduct += payouts[i];
			}
			
			int numOverZero = 0;
			for (int i = 0; i < COINS.length; i++) {
				if (COINS[i] >= 100) {
					numOverZero++;
				}
			}
			
			double averagePayout = (double)totalProduct / (double)nCombinations;
			double expectedReturn = (double)totalProduct / (double)nCombinations;
			
			System.out.printf("Average Payout: %.4fx\n", averagePayout);
			System.out.printf("Expected Return: %.4f%%\n", 100*expectedReturn);
			System.out.printf("House Edge: %.4f%%\n", 100*(1-expectedReturn));
			System.out.printf("Odds Of Any Specific Combo: 1 / %d\n", nCombinations);
			System.out.printf("Odds Of Any Specific Coin in a Reel: 1 / %d\n", NUM_COINS);
			System.out.printf("Coins Over 1x: %d / %d\n", numOverZero, NUM_COINS);
			System.out.printf("Top Payout:  %.6f\n", Math.pow((double)COINS[0] / 100, NUM_ROWS));					
			System.out.printf("Lowest Payout:  %.9f\n\n", Math.pow(COINS[COINS.length - 1], NUM_ROWS) / (int)Math.pow(100, NUM_ROWS));
			
			double[] gameOutputs = runSim();
			if (!skipBreakdown) {
				runGameBreakdown(gameOutputs);
			}
		}
}
