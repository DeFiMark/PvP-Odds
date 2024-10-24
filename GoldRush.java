package main;

import java.util.Random;

public class GoldRush {
	
	public static void printMineSweepPayouts() {
		
		int[] cells = { 9, 16, 25 };
		double reduction = 0.987;
		
		double precision = 100_000;
		int numItems = 0;
		
		int[] cellCounts = new int[456];
		int[] gemCounts = new int[456];
		int[] guessCounts = new int[456];
		long[] payoutCounts = new long[456];
		
		for (int i = 0; i < cells.length; i++) {
			int cellCount = cells[i];
			
			for (int numGems = 1; numGems < cellCount; numGems++) {
				
				for (int numGuesses = 1; numGuesses <= numGems; numGuesses++) {
					
					double payout = 1.0;
					
					for (int g = 0; g < numGuesses; g++) {
						double nextMultiplier = (double)( cellCount - g ) / (double)( numGems - g );
						payout *= nextMultiplier;
					}
					
					payout *= reduction;
					long payoutToPrint = (long)Math.floor(precision * payout);
					System.out.printf("payouts[%d][%d][%d] = %d;\n", cellCount, numGems, numGuesses, payoutToPrint);
					
					cellCounts[numItems] = cellCount;
					gemCounts[numItems] = numGems;
					guessCounts[numItems] = numGuesses;
					payoutCounts[numItems] = payoutToPrint;
					
					numItems++;
					
					// we have the guesses, gems, and cell count
					// do math to determine their payout
//					System.out.printf("Cell Count: %d | Num Gems: %d | Num Guesses: %d  ==> Payout: %d\n", cellCount, numGems, numGuesses, payoutToPrint);
					
				}
				
			}
		}
		
		System.out.println("Num Items: " + numItems);
		System.out.println("\n\n");
		
		System.out.print("[");
		for (int i = 0; i < cellCounts.length; i++) {
			System.out.print(cellCounts[i] + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < gemCounts.length; i++) {
			System.out.print(gemCounts[i] + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < guessCounts.length; i++) {
			System.out.print(guessCounts[i] + ",");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < payoutCounts.length; i++) {
			System.out.print(payoutCounts[i] + ",");
		}
		System.out.print("]\n");
	}

	public static void runMineSweep() {
		System.out.println("Simulating Mine Sweep\n");
		
		
		int numCells   = 25;
		int numGems    = 15;
		int numMines = numCells - numGems;
		int numGuesses = 15;
		
		int numRuns = 200_000_000;
		Random r = new Random();
		
		
		double bet = 10.0;
		double userValue = 0;
		double houseValue = 0;
		double totalBet = numRuns * bet;
		
		double platformFee = 0.01;
		double payoutReduction = 0.987;
		
		double platformFeeAmount = bet * platformFee;
		double amountForHouse = bet - platformFeeAmount;
		
		double winningMultiplier = 1.0;
		
		int numWins = 0;
		int numLosses = 0;
		
		for (int i = 0; i < numRuns; i++) {
			
			houseValue += amountForHouse;
			userValue -= bet;
//			winningMultiplier = 1.0;
			
			double totalPayoutMultiplier = 1.0;
//			int currentGems = numGems;
			
			for (int g = 0; g < numGuesses; g++) {
				
				// take bet from user, add it to the house
//				userAmount -= bet;
//				houseAmount += amountForHouse;
				
				int rng = r.nextInt(Integer.MAX_VALUE) % (numCells - g);
				
				if (rng >= numMines) {
					// we picked a gem on this guess
					double nextMultiplier = (double)( numCells - g ) / (double)( numGems - g );
					totalPayoutMultiplier *= nextMultiplier;

				} else {
					// we picked a bomb on this guess
					// destroy all earnings, user wins nothing from this game
					totalPayoutMultiplier = 0;
					break;
				}
			}
			
//			houseValue += houseAmount;
//			userValue += userAmount;
			if (totalPayoutMultiplier > 0) {
				double payoutAmount = bet * totalPayoutMultiplier * payoutReduction;
				houseValue -= payoutAmount;
				userValue += payoutAmount;
				numWins++;
				winningMultiplier = totalPayoutMultiplier * payoutReduction;
			} else {
				numLosses++;
			}
			
		}
		
		System.out.printf("User Value: %.2f\n", userValue);
		System.out.printf("House Value: %.2f\n", houseValue);
		System.out.printf("Total Bet:  %.2f\n\n", totalBet);
		System.out.printf("Num Wins: %d\nNum Losses: %d\n", numWins, numLosses);
		System.out.printf("Win Rate: %.5f%%\n", 100.0 * (double)numWins / ((double)numLosses + (double)numWins));
		System.out.printf("Multiplier Won: %.3fx\n\n",winningMultiplier);
		System.out.printf("House Edge: %.5f%%\n", ( houseValue * 100 ) / totalBet);
		System.out.printf("User Loss: %.5f%%\n", ( userValue * 100 ) / totalBet);
		System.out.printf("Return To Player: %.5f%%\n", 100 + (( userValue * 100 ) / totalBet));
	}
	
	
	
	public static void run() {
		System.out.println("Simulating Gold Rush\n");
		
		
		int numCells   = 100;
		int numGems    = 14;
		int numGuesses = 4;
		
		int numRuns = 500_000_000;
		Random r = new Random();
		
		
		double bet = 1;
		double userValue = 0;
		double houseValue = 0;
		double totalBet = numRuns * numGuesses * bet;
		
		double platformFee = 0.01;
		double payoutReduction = 0.987;
		
		double platformFeeAmount = bet * platformFee;
		double amountForHouse = bet - platformFeeAmount;
		
		for (int i = 0; i < numRuns; i++) {
			
			int currentGems = numGems;
			for (int g = 0; g < numGuesses; g++) {
				
				// take bet from user, add it to the house
				userValue -= bet;
				
				houseValue += amountForHouse;
				
				int rng = r.nextInt(Integer.MAX_VALUE) % (numCells - g);
				
				if (rng < currentGems) {
					// we picked a gem on this guess
					double payoutMultiplier = (double)( (double)numCells - g ) / (double)currentGems;
					double payout = bet * payoutMultiplier * payoutReduction;
					
					// add payout to user, subtract from house
					userValue += payout;
					houseValue -= payout;
					
					// reduce the number of gems to get by 1
					currentGems -= 1;
				}
			}
		}
		
		System.out.printf("User Value: %.2f\n", userValue);
		System.out.printf("House Value: %.2f\n", houseValue);
		System.out.printf("Total Bet:  %.2f\n\n", totalBet);
		System.out.printf("House Edge: %.5f%%\n", ( houseValue * 100 ) / totalBet);
		System.out.printf("User Loss: %.5f%%\n", ( userValue * 100 ) / totalBet);
		System.out.printf("Return To Player: %.5f%%\n", 100 + (( userValue * 100 ) / totalBet));
	}
}
