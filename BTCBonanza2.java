package main;

import java.util.Random;

public class BTCBonanza2 {
	public enum Coins {
		BTC,
		ETH,
		BNB,
		SOL,
		DOG,
		MAT
	}
	
	public static int NUM_COINS = 6;
	
	public static final Coins[][] combos = {
			{Coins.BTC, Coins.BTC, Coins.BTC},
			{Coins.ETH, Coins.ETH, Coins.ETH},
			{Coins.BNB, Coins.BNB, Coins.BNB},
			{Coins.SOL, Coins.SOL, Coins.SOL},
			{Coins.MAT, Coins.MAT, Coins.MAT},
			{Coins.DOG, Coins.DOG, Coins.DOG},
			
			
			{Coins.BTC, Coins.BTC, Coins.ETH},
			{Coins.BTC, Coins.BTC, Coins.BNB},
			{Coins.BTC, Coins.BTC, Coins.SOL},
			{Coins.BTC, Coins.BTC, Coins.MAT},
			{Coins.BTC, Coins.BTC, Coins.DOG},
			
			
			{Coins.BTC, Coins.ETH, Coins.BTC},
			{Coins.BTC, Coins.ETH, Coins.ETH},
			{Coins.BTC, Coins.ETH, Coins.BNB},
			{Coins.BTC, Coins.ETH, Coins.SOL},
			{Coins.BTC, Coins.ETH, Coins.DOG},
			{Coins.BTC, Coins.ETH, Coins.MAT},
			
			{Coins.BTC, Coins.BNB, Coins.BTC},
			{Coins.BTC, Coins.BNB, Coins.ETH},
			{Coins.BTC, Coins.BNB, Coins.BNB},
			{Coins.BTC, Coins.BNB, Coins.SOL},
			{Coins.BTC, Coins.BNB, Coins.DOG},
			{Coins.BTC, Coins.BNB, Coins.MAT},
			
			{Coins.BTC, Coins.DOG, Coins.BTC},
			{Coins.BTC, Coins.DOG, Coins.ETH},
			{Coins.BTC, Coins.DOG, Coins.BNB},
			{Coins.BTC, Coins.DOG, Coins.SOL},
			{Coins.BTC, Coins.DOG, Coins.MAT},
			{Coins.BTC, Coins.DOG, Coins.DOG},
			
			{Coins.BTC, Coins.MAT, Coins.BTC},
			{Coins.BTC, Coins.MAT, Coins.ETH},
			{Coins.BTC, Coins.MAT, Coins.BNB},
			{Coins.BTC, Coins.MAT, Coins.SOL},
			{Coins.BTC, Coins.MAT, Coins.MAT},
			{Coins.BTC, Coins.MAT, Coins.DOG},

			{Coins.BTC, Coins.SOL, Coins.BTC},
			{Coins.BTC, Coins.SOL, Coins.ETH},
			{Coins.BTC, Coins.SOL, Coins.BNB},
			{Coins.BTC, Coins.SOL, Coins.SOL},
			{Coins.BTC, Coins.SOL, Coins.MAT},
			{Coins.BTC, Coins.SOL, Coins.DOG},
			
			
			{Coins.ETH, Coins.BTC, Coins.ETH},
			{Coins.ETH, Coins.BTC, Coins.BTC},
			{Coins.ETH, Coins.BTC, Coins.BNB},
			{Coins.ETH, Coins.BTC, Coins.MAT},
			{Coins.ETH, Coins.BTC, Coins.SOL},
			{Coins.ETH, Coins.BTC, Coins.DOG},
			
			{Coins.ETH, Coins.ETH, Coins.BTC},
			{Coins.ETH, Coins.ETH, Coins.BNB},
			{Coins.ETH, Coins.ETH, Coins.SOL},
			{Coins.ETH, Coins.ETH, Coins.MAT},
			{Coins.ETH, Coins.ETH, Coins.DOG},
			
			{Coins.ETH, Coins.BNB, Coins.BTC},
			{Coins.ETH, Coins.BNB, Coins.BNB},
			{Coins.ETH, Coins.BNB, Coins.ETH},
			{Coins.ETH, Coins.BNB, Coins.SOL},
			{Coins.ETH, Coins.BNB, Coins.MAT},
			{Coins.ETH, Coins.BNB, Coins.DOG},
			
			{Coins.ETH, Coins.SOL, Coins.BTC},
			{Coins.ETH, Coins.SOL, Coins.ETH},
			{Coins.ETH, Coins.SOL, Coins.BNB},
			{Coins.ETH, Coins.SOL, Coins.SOL},
			{Coins.ETH, Coins.SOL, Coins.DOG},
			{Coins.ETH, Coins.SOL, Coins.MAT},
			
			{Coins.ETH, Coins.DOG, Coins.BTC},
			{Coins.ETH, Coins.DOG, Coins.ETH},
			{Coins.ETH, Coins.DOG, Coins.BNB},
			
			{Coins.ETH, Coins.MAT, Coins.BNB},
			{Coins.ETH, Coins.MAT, Coins.ETH},
			{Coins.ETH, Coins.MAT, Coins.BTC},
			{Coins.ETH, Coins.MAT, Coins.MAT},
			{Coins.ETH, Coins.MAT, Coins.DOG},
			{Coins.ETH, Coins.MAT, Coins.SOL},
			
			
			{Coins.BNB, Coins.BTC, Coins.BTC},
			{Coins.BNB, Coins.BTC, Coins.ETH},
			{Coins.BNB, Coins.BTC, Coins.BNB},
			{Coins.BNB, Coins.BTC, Coins.SOL},
			{Coins.BNB, Coins.BTC, Coins.DOG},
			{Coins.BNB, Coins.BTC, Coins.MAT},
			
			{Coins.BNB, Coins.ETH, Coins.BTC},
			{Coins.BNB, Coins.ETH, Coins.ETH},
			{Coins.BNB, Coins.ETH, Coins.BNB},
			{Coins.BNB, Coins.ETH, Coins.SOL},
			{Coins.BNB, Coins.ETH, Coins.DOG},
			{Coins.BNB, Coins.ETH, Coins.MAT},
			
			{Coins.BNB, Coins.BNB, Coins.BTC},
			{Coins.BNB, Coins.BNB, Coins.ETH},
			
			{Coins.SOL, Coins.BTC, Coins.BTC},
			{Coins.DOG, Coins.BTC, Coins.BTC},
			{Coins.MAT, Coins.BTC, Coins.BTC},
			
			{Coins.SOL, Coins.BTC, Coins.ETH},
			{Coins.DOG, Coins.BTC, Coins.ETH},
			{Coins.MAT, Coins.BTC, Coins.ETH},
			
			{Coins.SOL, Coins.ETH, Coins.BTC},
			{Coins.DOG, Coins.ETH, Coins.BTC},
			{Coins.MAT, Coins.ETH, Coins.BTC},
			
			{Coins.SOL, Coins.ETH, Coins.ETH},
			{Coins.DOG, Coins.ETH, Coins.ETH},
			{Coins.MAT, Coins.ETH, Coins.ETH},
			
			{Coins.SOL, Coins.SOL, Coins.BTC},
			{Coins.DOG, Coins.DOG, Coins.BTC},
			{Coins.MAT, Coins.MAT, Coins.BTC},
			
			{Coins.SOL, Coins.SOL, Coins.ETH},
			{Coins.DOG, Coins.DOG, Coins.ETH},
			{Coins.MAT, Coins.MAT, Coins.ETH},
			
			{Coins.SOL, Coins.BTC, Coins.SOL},
			{Coins.DOG, Coins.BTC, Coins.DOG},
			{Coins.MAT, Coins.BTC, Coins.MAT},
			
			{Coins.SOL, Coins.ETH, Coins.SOL},
			{Coins.DOG, Coins.ETH, Coins.DOG},
			{Coins.MAT, Coins.ETH, Coins.MAT},
	};
	
	 									//   0		 1		2	   3	  4	     5 
	public static final String[] options = {"BTC", "ETH", "BNB", "SOL", "DOGE", "MATIC"};
	
								    // BTC  PEPE  SHIB  RUBBER DOGE FLOKI
	public static final int[] reel1 = { 20,   20,    30,     30,    50,    50 };
	public static final int[] reel2 = { 10,   20,    30,     40,    50,    50 };
	public static final int[] reel3 = { 10,   20,    40,     40,    40,    50 };
	
	public static final int[] combinedReel1 = { 20, 40, 70, 100, 150, 200 };
	public static final int[] combinedReel2 = { 10, 30, 60, 100, 150, 200 };
	public static final int[] combinedReel3 = { 10, 30, 70, 110, 150, 200 };

//	public static double payoutBasisPoint = 60.0;
//	public static double bitcoinIncrease = 1.1;
	
	public static double payoutBasisPoint = 50.0;
	public static double bitcoinIncrease = 1.237;
	
	public static void run() {
		System.out.println("Running Slots");
		
		int reel1Total = 0;
		int reel2Total = 0;
		int reel3Total = 0;
		boolean copyPresent = false;

		
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
			realPayouts[i] = ((double)Math.round((100_000 * payoutBasisPoint ) / ((double)waysToMakeCombo[i] / 1000))) / 100_000.0;
			
			
			
			if (combos[i][0] == Coins.BTC || combos[i][1] == Coins.BTC || combos[i][2] == Coins.BTC) {
				realPayouts[i] *= bitcoinIncrease;
			}
			
			
			
			if (combos[i][0] == Coins.DOG && combos[i][1] == Coins.DOG && combos[i][2] == Coins.DOG) {
				realPayouts[i] = 3;
			}
			else if (combos[i][0] == Coins.BNB && combos[i][1] == Coins.BNB && combos[i][2] == Coins.BNB) {
				realPayouts[i] = 4;
			}
			else if (combos[i][0] == Coins.MAT && combos[i][1] == Coins.MAT && combos[i][2] == Coins.MAT) {
				realPayouts[i] = 2.5;
			}
			else if (combos[i][0] == Coins.BTC && combos[i][1] == Coins.BTC && combos[i][2] == Coins.BTC) {
				realPayouts[i] = 100;
			}
			else if (combos[i][0] == Coins.ETH && combos[i][1] == Coins.ETH && combos[i][2] == Coins.ETH) {
				realPayouts[i] = 11;
			}
			else if (combos[i][0] == Coins.SOL && combos[i][1] == Coins.SOL && combos[i][2] == Coins.SOL) {
				realPayouts[i] = 3;
			} 
			
			
			if (realPayouts[i] < 2) {
				System.out.println("BELOW 2: " + combos[i][0] + " " + combos[i][1] + " " + combos[i][2] + " => " + realPayouts[i]);
				realPayouts[i] = 2;
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
		
//		for (int i = 0; i < combos.length; i++) {
//			System.out.println("Ways To Make Combo: " + waysToMakeCombo[i]);
//		}
		
//		for (int i = 0; i < combos.length; i++) {
//			System.out.println("Products: " + productOfWaysAndPayouts[i]);
//		}
		
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
		
		for (int i = 0; i < sortedCombos.length; i++) {
			
			String str = sortedCombos[i][0] + 
					": { " + 
					sortedCombos[i][1] + " : { " + 
					sortedCombos[i][2] + ": " + sortedCombos[i][3] + 
					" } },";
			System.out.println(str);
		}
		
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
		System.out.println(unused + " not used: ");
		
		System.out.println(allCombos[unused][0] + ", " + allCombos[unused][1] + ", " + allCombos[unused][2] + ", " + allCombos[unused][3]);
		
		String s = "";
		
		for (int i = 0; i < NUM_COINS; i++) {
			
			for (int j = 0; j < NUM_COINS; j++) {
				
				System.out.println(i + ":" + j + ":" + comboLengths[i][j][0]);
			}
		}
		
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
		int runs = 200_000_000;
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
		
		double jackpotFee = 0; // 0.05%
		double currentJackpotFees = 0;
		double totalJackpotFees = 0;
		double averageJackpotBonus = 0;
		
		double averagePayout_ = 0;
		
		int random0 = 0;
		int random1 = 0;
		int random2 = 0;
		
		int coin0 = 0;
		int coin1 = 0;
		int coin2 = 0;
		
		Random r = new Random();
		
		int SPINS_PER_GAME = 20;
		int GAMES_TO_TRACK = 5000;
		double gameBuyIn = SPINS_PER_GAME * bet;
		double totalWonThisGame = 0;
		
		double[] gameOutputs = new double[GAMES_TO_TRACK];
		
		for (int i = 0; i < runs; i++) {
			if (i > 0 && i % SPINS_PER_GAME == 0) {
				int index = (i / SPINS_PER_GAME) - 1;
				if (index < GAMES_TO_TRACK) {
					gameOutputs[index] = totalWonThisGame - gameBuyIn;
					totalWonThisGame = 0;
				}
			}
			
			currentValue -= bet;
			
			double pFee = bet * fee;
			double jFee = bet * jackpotFee;
			totalFees += pFee;
			totalJackpotFees += jFee;
			currentJackpotFees += jFee;
		
			houseValue += (bet - (pFee + jFee));
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
			
			int payoutMultiplier = getPayout[coin0][coin1][coin2][0];
			if (payoutMultiplier > 0) {
				if (coin0 == 0 && coin1 == 0 && coin2 == 0) {
					numJackpots++;
					currentValue += currentJackpotFees;
					averageJackpotBonus = ( ( averageJackpotBonus * ( numJackpots - 1 ) ) + currentJackpotFees ) / numJackpots;
					currentJackpotFees = 0;
				}
				numWins++;
				double valueEarned = (bet * (double)payoutMultiplier) / 10_000.0;
				currentValue += valueEarned;
				houseValue -= valueEarned;
				houseValueNoFees -= valueEarned;
				
				totalWonThisGame += valueEarned;
				
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
		System.out.printf("Average Jackpot Bonus: $%.2f\n", averageJackpotBonus);
		System.out.printf("Average Payout: %.3fx\n", averagePayout_);
		System.out.printf("House Edge: %.4f%%\n", 100 * houseValue / (runs * bet));
		System.out.printf("House Edge (No Fees): %.4f%%\n", 100 * houseValueNoFees / (runs * bet));
		System.out.printf("Expected Return (User): %.4f%%\n", 100 * ( 1 + ( currentValue / ( runs * bet ))));
		System.out.printf("User Profit: %.2f\n", ( currentValue - startingValue ));
		if (numLosses > 0) {
			return;
		}
		
		System.out.printf("\n\n\nPrinting Output from %d Games for %d spins per game at %d per spin:\n\n", GAMES_TO_TRACK, SPINS_PER_GAME, (int)bet);
		System.out.println("Claim   |  Result   |  Percent Diff");
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
}
