package main;
import java.util.Random;

public class MemecoinBoost {
	public enum Coins {
		BTC,
		PEPE,
		SHIB,
		DUCK,
		DOG
	}
	
	public static int NUM_COINS = 5;
	
	 									//   0		 1		2	   3	  4	  
	public static final String[] options = {"BTC", "PEPE", "SHIB", "DUCK", "DOGE"};
	public static final Coins[] coinOptions = {Coins.BTC, Coins.PEPE, Coins.SHIB, Coins.DUCK, Coins.DOG};
								    // BTC  PEPE  FLOKI  DOGE
	public static final int[] reel1 = { 1,   2,   3,    3,    4};
	public static final int[] reel2 = { 1,   2,   3,    3,    5};
	public static final int[] reel3 = { 1,   2,   3,    3,    6};
	
	public static final int[] combinedReel1 = { 1, 3, 6, 9, 13 };
	public static final int[] combinedReel2 = { 1, 3, 6, 9, 14 };
	public static final int[] combinedReel3 = { 1, 3, 6, 9, 15 };

	public static double payoutBasisPoint = 18;
	public static double btcBoost = 1;

	public static int factorial(int num) {
		if (num <= 1) {
			return 1;
		}
		int fact = num;
		for (int i = num - 1; i >= 1; i--) {
			fact *= i;
		}
		return fact;
	}
	
	public static void run() {
		System.out.println("Running Slots");
		
		int reel1Total = 0;
		int reel2Total = 0;
		int reel3Total = 0;
		boolean copyPresent = false;
		
		int fact = NUM_COINS * NUM_COINS * NUM_COINS;//factorial(NUM_COINS);
		Coins[][] combos = new Coins[fact][3];
		int counter = 0;
		for (int i = 0; i < NUM_COINS; i++) {
			for (int j = 0; j < NUM_COINS; j++) {
				for (int z = 0; z < NUM_COINS; z++) {
					Coins[] combo = new Coins[3];
					combo[0] = coinOptions[i];
					combo[1] = coinOptions[j];
					combo[2] = coinOptions[z];
					combos[counter] = combo;
					counter++;
				}
			}
		}
		System.out.println("Num Combos: " + combos.length + " " + counter);
		
		for (int i = 0; i < options.length; i++) {
			reel1Total += reel1[i];
			reel2Total += reel2[i];
			reel3Total += reel3[i];
		}
		
		int nCombinations = reel1Total * reel2Total * reel3Total;
		
		int[][] allCombos = new int[combos.length][4];
		
		double[] realPayouts = new double[combos.length];
		
		int[][][][] getPayout = new int[NUM_COINS][NUM_COINS][NUM_COINS][1];
		
		int[] waysToMakeCombo = new int[combos.length];
		double[] productOfWaysAndPayouts = new double[combos.length];
		int totalWaysToWin = 0;
		double totalProduct = 0;
		double weightedSum = 0;
		for (int i = 0; i < combos.length; i++) {
			
			waysToMakeCombo[i] = reel1[combos[i][0].ordinal()] * reel2[combos[i][1].ordinal()] * reel3[combos[i][2].ordinal()];
			
			//realPayouts[i] = ((double)Math.round((10_000 * payoutBasisPoint ) / (double)waysToMakeCombo[i])) / 10_000.0;
			realPayouts[i] = ((double)Math.round((10_000 * payoutBasisPoint ) / (double)waysToMakeCombo[i])) / 10_000.0;
			
//			if (combos[i][0] == Coins.BTC || combos[i][1] == Coins.BTC || combos[i][2] == Coins.BTC) {
//				realPayouts[i] *= btcBoost;
//			}
			
//			if (realPayouts[i] <= 0.75) {
//				System.out.println("Combo: " + combos[i][0] + " " + combos[i][1] + " " + combos[i][2] + " => " + realPayouts[i] + "x  => 0.7x");
//				realPayouts[i] = 0.7;
//			}
//			else if (realPayouts[i] <= 0.8) {
//				realPayouts[i] = 0.8;
//			}
			if (realPayouts[i] <= 1.25) {
				System.out.println("Combo: " + combos[i][0] + " " + combos[i][1] + " " + combos[i][2] + " => " + realPayouts[i] + "x  => 1.2x");
				realPayouts[i] = 1.25;
			}
//			else if (realPayouts[i] <= 1.5) {
//				realPayouts[i] = 1.5;
//			} 
			else if (realPayouts[i] <= 2) {
//				System.out.println("Combo: " + combos[i][0] + " " + combos[i][1] + " " + combos[i][2] + " => " + realPayouts[i] + "x  => 2x");
				realPayouts[i] = 2;
			} 
			else if (realPayouts[i] <= 2.5) {
				realPayouts[i] = 2.5;
			} 
			else if (realPayouts[i] <= 3) {
				realPayouts[i] = 3;
			} 
			else if (realPayouts[i] <= 5) {
				realPayouts[i] = 5;
			} 
//			else if (realPayouts[i] <= 6) {
//				realPayouts[i] = 6;
//			}
//			else {
//				realPayouts[i] *= 1.17;
//			}
//			else if (realPayouts[i] < 2) {
//				realPayouts[i] = 1.5;
//			}
			
			if (combos[i][0] == Coins.BTC && combos[i][1] == Coins.BTC && combos[i][2] == Coins.BTC) {
				realPayouts[i] = 60;
			}
			else if (combos[i][0] == Coins.PEPE && combos[i][1] == Coins.PEPE && combos[i][2] == Coins.PEPE) {
				realPayouts[i] = 6;
			}
			else if (combos[i][0] == Coins.SHIB && combos[i][1] == Coins.SHIB && combos[i][2] == Coins.SHIB) {
				realPayouts[i] = 1.5;
			}
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.DUCK && combos[i][2] == Coins.DUCK) {
				realPayouts[i] = 1.4;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0.25;
			}
			
			if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DUCK) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DOG && combos[i][2] == Coins.SHIB) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DOG && combos[i][2] == Coins.PEPE) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DUCK && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DUCK && combos[i][2] == Coins.DUCK) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DUCK && combos[i][2] == Coins.SHIB) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.SHIB && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.SHIB && combos[i][2] == Coins.DUCK) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.SHIB && combos[i][2] == Coins.SHIB) {
				realPayouts[i] = 0;
			}
			
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DUCK) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.DOG && combos[i][2] == Coins.SHIB) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.DOG && combos[i][2] == Coins.PEPE) {
				realPayouts[i] = 0;
			}
			
			// NEW NON COMBOS
			else if (combos[i][0] == Coins.SHIB && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.SHIB && combos[i][1] == Coins.SHIB && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.PEPE && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.PEPE && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.DUCK && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}
			else if (combos[i][0] == Coins.DUCK && combos[i][1] == Coins.SHIB && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 0;
			}

			productOfWaysAndPayouts[i] = waysToMakeCombo[i] * realPayouts[i];
			totalWaysToWin += waysToMakeCombo[i];
			totalProduct += productOfWaysAndPayouts[i];
			
			weightedSum += ( (double)waysToMakeCombo[i] * (double)realPayouts[i] ) / ((double)nCombinations);
			allCombos[i][0] = combos[i][0].ordinal();
			allCombos[i][1] = combos[i][1].ordinal();
			allCombos[i][2] = combos[i][2].ordinal();
			allCombos[i][3] = (int)(10_000 * realPayouts[i]);
			
			if (getPayout[combos[i][0].ordinal()][combos[i][1].ordinal()][combos[i][2].ordinal()][0] > 0) {
				System.out.println("COPY FOUND!!!!!! " + combos[i][0].ordinal() + " " + combos[i][1].ordinal() + " " + combos[i][2].ordinal());
				copyPresent = true;
			}
			
			getPayout[combos[i][0].ordinal()][combos[i][1].ordinal()][combos[i][2].ordinal()][0] = (int)(10_000 * realPayouts[i]);
		}
		
		
		double averagePayout = 0;
		int startIndex = 0;
		for (int i = startIndex; i < realPayouts.length; i++) {
			averagePayout += realPayouts[i];
		}
		averagePayout /= ( realPayouts.length - startIndex );
		
		double winProbability = (double)totalWaysToWin / (double)nCombinations;
		double expectedReturn = (double)totalProduct / (double)nCombinations;
		
		System.out.println("COMBOS        |   WAYS  |   PAYOUTS     |     ODDS");
		for (int i = 0; i < combos.length; i++) {
			System.out.printf(
					"%s %s %s   |   %d     |   %.2f     |    %.5f%%  |  1 / %.2f\n", 
					combos[i][0],
					combos[i][1],
					combos[i][2],
					waysToMakeCombo[i],
					realPayouts[i], 
					100*(double)waysToMakeCombo[i]/(double)nCombinations,
					(double)nCombinations / (double)waysToMakeCombo[i]
			);
		}
		System.out.println("\n\n");
		
		for (int i = 0; i < combos.length; i++) {
			System.out.println(combos[i][0] + "," + combos[i][1] + "," + combos[i][2] + "  => " + (realPayouts[i]) + "x");
		}
		
		System.out.printf("\n\nProbability of Winning: %.4f%%\n", 100*winProbability);
		System.out.printf("Average Payout: %.4fx\n", averagePayout);
		System.out.printf("Expected Return: %.4f%%\n", 100*expectedReturn);
		System.out.printf("House Edge: %.4f%%\n", 100*(1-expectedReturn));
		System.out.printf("Weighted Sum Buy-In: %.4f\n", weightedSum);
		System.out.printf("Reel1Total: %d\nReel2Total: %d\nReel3Total: %d\nTotal Combinations: %d\n\n", reel1Total, reel2Total, reel3Total, nCombinations);
		System.out.println("Total Ways To Win: " + totalWaysToWin);
		System.out.println("Total Product: " + totalProduct + "\n\n");
		
		System.out.println("Printing configs as they should be in the contract");
		System.out.print("[");
		int total1 = 0;
		for (int i = 0; i < reel1.length; i++) {
			total1 += reel1[i];
			System.out.print(total1 + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		int total2 = 0;
		for (int i = 0; i < reel2.length; i++) {
			total2 += reel2[i];
			System.out.print(total2 + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		int total3 = 0;
		for (int i = 0; i < reel3.length; i++) {
			total3 += reel3[i];
			System.out.print(total3 + ",");
		}
		System.out.print("]\n\n\n");
		
		System.out.printf("Printing %d Combos\n", realPayouts.length);
		
		System.out.print("[");
		for (int i = 0; i < combos.length; i++) {
			System.out.print(combos[i][0].ordinal() + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < combos.length; i++) {
			System.out.print(combos[i][1].ordinal() + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < combos.length; i++) {
			System.out.print(combos[i][2].ordinal() + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < realPayouts.length; i++) {
			System.out.print((int)(realPayouts[i] * 10_000) + ",");
		}
		System.out.print("]\n\n\n");
		
		
		System.out.println("Printing For Typescript\n\n");
		
		boolean[] used = new boolean[allCombos.length];
		int startingNum = 0;
		int secondNum = 0;
		int numsUsed = 0;
		int usedInCombo = 0;
		
		int[][] sortedCombos = new int[allCombos.length - 1][4];
		
		int[][][] comboLengths = new int[NUM_COINS][NUM_COINS][1];
		
		for (int i = 0; i < allCombos.length; i++) {
			if (i == allCombos.length - 1) {
				comboLengths[startingNum][secondNum][0] = usedInCombo;
				usedInCombo = 0;
				secondNum++;
				if (secondNum == NUM_COINS) {
					secondNum = 0;
					startingNum++;
				}
				if (startingNum < NUM_COINS) {
					i = 0;
				}
			}
			if (used[i]) {
				continue;
			}
			
			if (allCombos[i][0] == startingNum && allCombos[i][1] == secondNum) {
				
				sortedCombos[numsUsed][0] = allCombos[i][0];
				sortedCombos[numsUsed][1] = allCombos[i][1];
				sortedCombos[numsUsed][2] = allCombos[i][2];
				sortedCombos[numsUsed][3] = allCombos[i][3];
				
				numsUsed++;
				usedInCombo++;
				used[i] = true;
//				String str = allCombos[i][0] + 
//						": { " + 
//						allCombos[i][1] + " : { " + 
//						allCombos[i][2] + ": " + allCombos[i][3] + 
//						" } },";
//				System.out.println(str);
			}
			
		}
		
//		for (int i = 0; i < sortedCombos.length; i++) {
//			
//			String str = sortedCombos[i][0] + 
//					": { " + 
//					sortedCombos[i][1] + " : { " + 
//					sortedCombos[i][2] + ": " + sortedCombos[i][3] + 
//					" } },";
//			System.out.println(str);
//		}
		
		int unused = 0;
		for (int i = 0; i < allCombos.length; i++) {
			if (!used[i]) {
				unused = i;
//				String str = allCombos[i][0] + 
//						": { " + 
//						allCombos[i][1] + " : { " + 
//						allCombos[i][2] + ": " + allCombos[i][3] + 
//						" } },";
//				System.out.println(str);
			}
		}
	
		
		System.out.println("Nums Written: " + numsUsed + " / " + allCombos.length);
		System.out.println("#" + unused + " not used: ");
		
		System.out.println(allCombos[unused][0] + ", " + allCombos[unused][1] + ", " + allCombos[unused][2] + ", " + allCombos[unused][3]);
		
		String s = "";
		
//		for (int i = 0; i < NUM_COINS; i++) {
//			
//			for (int j = 0; j < NUM_COINS; j++) {
//				
//				System.out.println(i + ":" + j + ":" + comboLengths[i][j][0]);
//			}
//		}
		
		int count = 0;
		for (int i = 0; i < NUM_COINS; i++) {
			s += i + ": {";
			for (int j = 0; j < NUM_COINS; j++) {
				if (comboLengths[i][j][0] == 0) {
					continue;
				}
				s += j + ": {";
				for (int z = 0; z < comboLengths[i][j][0]; z++) {
					s += sortedCombos[count][2] + ": " + sortedCombos[count][3] + ",";
					count++;
				}
				s += "},";
//				System.out.println(i + ":" + j + ":" + comboLengths[i][j][0]);
			}
			s += "},";
		}
		System.out.println(s);
		System.out.println("Count: " +count);
		
		System.out.printf("\n\nProbability of Winning: %.4f%%\n", 100*winProbability);
		System.out.printf("Average Payout: %.4fx\n", averagePayout);
		System.out.printf("Expected Return: %.4f%%\n", 100*expectedReturn);
		System.out.printf("House Edge: %.4f%%\n", 100*(1-expectedReturn));
		System.out.printf("Weighted Sum Buy-In: %.4f\n", weightedSum);
		System.out.printf("Reel1Total: %d\nReel2Total: %d\nReel3Total: %d\nTotal Combinations: %d\n\n", reel1Total, reel2Total, reel3Total, nCombinations);
		System.out.println("Total Ways To Win: " + totalWaysToWin);
		System.out.println("Total Product: " + totalProduct + "\n\n");
		
		System.out.println("Any Copies: " + copyPresent + "\n");
		
		
		/** BEGIN SIMULATING OVER DATA*/
		int runs = 100_000_000;
		double startingValue = 0;
		double currentValue = startingValue;
		double bet = 1;
		double houseValue = 0;
		double houseValueNoFees = 0;
		int numWins = 0;
		int numLosses = 0;
		int numJackpots = 0;
		
		double fee = 0.01; // 1%
		double totalFees = 0;
		double averagePayout_ = 0;
		
		int random0 = 0;
		int random1 = 0;
		int random2 = 0;
		
		int coin0 = 0;
		int coin1 = 0;
		int coin2 = 0;
		
		int odds_for_boost = 100;
		double reducedBuyIn = 0.31 * bet;
		
		Random r = new Random();
		
		int[] timesPayoutsOccur = new int[3];
		
		for (int i = 0; i < runs; i++) {
			
			currentValue -= bet;
			
			double pFee = bet * fee;
			totalFees += pFee;
		
			houseValue += ( bet - pFee );
			houseValueNoFees += bet;
			
			random0 = r.nextInt(Integer.MAX_VALUE) % combinedReel1[combinedReel1.length - 1];
			random1 = r.nextInt(Integer.MAX_VALUE) % combinedReel2[combinedReel2.length - 1];
			random2 = r.nextInt(Integer.MAX_VALUE) % combinedReel3[combinedReel3.length - 1];
			
			for (int j = 0; j < combinedReel1.length; j++) {
				if (random0 < combinedReel1[j]) {
					coin0 = j;
					break;
				}
			}
			for (int j = 0; j < combinedReel2.length; j++) {
				if (random1 < combinedReel2[j]) {
					coin1 = j;
					break;
				}
			}
			for (int j = 0; j < combinedReel3.length; j++) {
				if (random2 < combinedReel3[j]) {
					coin2 = j;
					break;
				}
			}
			
			if (coin0 > 0) {
				if (r.nextInt(100) < odds_for_boost) {
					coin0--;
				}
			}
			if (coin1 > 0) {
				if (r.nextInt(100) < odds_for_boost) {
					coin1--;
				}
			}
			if (coin2 > 0) {
				if (r.nextInt(100) < odds_for_boost) {
					coin2--;
				}
			}
			
			int payoutMultiplier = getPayout[coin0][coin1][coin2][0];
			if (payoutMultiplier > 0) {
				if (coin0 == 0 && coin1 == 0 && coin2 == 0) {
					numJackpots++;
				}
				if (payoutMultiplier == 7000) {
					timesPayoutsOccur[0]++;
				} else if (payoutMultiplier == 12000) {
					timesPayoutsOccur[1]++;
				} else if (payoutMultiplier == 20000) {
					timesPayoutsOccur[2]++;
				}
				numWins++;
				double valueEarned = (reducedBuyIn * (double)payoutMultiplier) / 10_000.0;
				currentValue += valueEarned;
				houseValue -= valueEarned;
				houseValueNoFees -= valueEarned;
				averagePayout_ = ( ( averagePayout_ * ( numWins - 1 ) ) + ((double)payoutMultiplier / 10_000.0) ) / numWins;
			} else {
				numLosses++;
			}
		}
		
		System.out.printf("running simulation %d times\n", runs);
//		System.out.printf("Current Value: %.2f\nHouse Value: %.2f\n", currentValue, houseValue);
		System.out.printf("Num Wins: %d\nNum Losses: %d\nNum Jackpots: %d\n", numWins, numLosses, numJackpots);
		System.out.printf("Win Rate: %.3f%%\n", 100.0*(double)numWins / runs);
		System.out.printf("Jackpot Odds: %.6f%%\n", 100.0*(double)numJackpots / runs);
//		System.out.printf("Total Fees: %.2f\nTotal Jackpot Fees: %.2f\n", totalFees, totalJackpotFees);
		System.out.printf("Average Payout: %.3fx\n", averagePayout_);
		System.out.printf("House Edge: %.4f%%\n", 100 * houseValue / (runs * bet));
		System.out.printf("House Edge (No Fees): %.4f%%\n", 100 * houseValueNoFees / (runs * bet));
		System.out.printf("Expected Return (User): %.4f%%\n", 100 * ( 1 + ( currentValue / ( runs * bet ))));
		System.out.printf("User Profit: %.2f\n", ( currentValue - startingValue ));
		
		countPayouts(realPayouts, 0.7);
		countPayouts(realPayouts, 1.2);
		countPayouts(realPayouts, 2);
		
		System.out.printf("0.7x Occured %d Times: %.5f%%\n", timesPayoutsOccur[0], (100.0 * timesPayoutsOccur[0] ) / runs);
		System.out.printf("1.2x Occured %d Times: %.5f%%\n", timesPayoutsOccur[1], (100.0 * timesPayoutsOccur[1] ) / runs);
		System.out.printf("2x Occured %d Times: %.5f%%\n", timesPayoutsOccur[2], (100.0 * timesPayoutsOccur[2] ) / runs);
		
	}
	
	
	public static int countPayouts(double[] payouts, double target) {
		int num = 0;
		int numOverZero = 0;
		for (int i = 0; i < payouts.length; i++) {
			if (payouts[i] == target) {
				num++;
			}
			if (payouts[i] > 0) {
				numOverZero++;
			}
		}
		System.out.printf("Payouts For %.3f: %d / %d\n", target, num, numOverZero);
		return num;
	}
	

}
