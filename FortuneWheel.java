package main;

import java.util.Random;

public class FortuneWheel {

	
	
	public static void run() {
		runNEWSim();
	}
	
	
	
	
	public static void runNEWSim() {
		System.out.println("Running New And Improved Fortune Wheel");
		
		int[] payouts = {  1,   2,   5,   10,   25,  50};
		int[] odds    = { 500, 420, 210,  100,  40,  20};
		// find odds where odds[i] * ( payouts[i] + 1 ) < totalOdds
		
		
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
		
		double totalBet = 0;
		for (int i = 0; i < guesses.length; i++) {
			totalBet += guesses[i] * bet;
		}
		
		int iterations = 50_000_000;
		
		Random r = new Random();
		
		double[] currentValuess = new double[guesses.length];
		double currentValuessTwo = 0;
		for (int currentGuessNo = 0; currentGuessNo < guesses.length; currentGuessNo++) {
			for (int i = 0; i < iterations; i++) {
				
				currentValuess[currentGuessNo] -= bet;
				currentValuessTwo -= totalBet;
				
				int index = r.nextInt(Integer.MAX_VALUE) % totalOptions;
				int winner = totalOdds.length;
				
				for (int j = 0; j < totalOdds.length; j++) {
					if (index < totalOdds[j]) {
						winner = j;
						break;
					}
				}
				
				double payout = ((double)payouts[winner] + 1) * bet;
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
