package main;

import java.util.Random;

public class FortuneWheel {

	
	public static double buyIn = 1;
	
	public static double[] _prizes = {
		2,   5,   0,  25,  8,    100,   0,   333,   0,   50,   25,   0,   50,   2.5,   0,   10,   5,   0,    2.5,  4,  0.5
	};
	
	public static int[] prizeWeights = {
		50, 30,  350, 15,  25,    4,   750,   1,   735,   8,   15,  500,   8,   40,   300,  15,  30,  350,   40,  30,  200
	};
	
//	public static double[] prizes_5 = {
//			1, 1, 1, 1, 2.5, 5, 2.5, 1, 1, 2.5, 10,
//			2.5, 1, 1, 25, 1, 1, 2.5, 1, 1, 1, 50, 
//			2.5, 1, 2.5, 1, 100, 2.5, 1, 1, 1, 2.5,
//			1, 1, 1, 2.5, 1, 1, 1, 2.5, 1, 1, 1, 
//			2.5, 1, 1, 1, 2.5, 1, 1, 1, 1
//		};
//	
//	public static double[] prizes_10 = {
//			1, 2.5, 2, 2, 1, 2.5, 2, 2, 1, 2.5, 2, 2, 1, 2.5, 2, 2,
//			5, 1, 10, 2, 25, 1, 5, 2.5, 100, 2, 1, 1, 250, 1, 1, 50, 
//			1, 1, 2, 2.5, 5, 17, 1, 2.5, 5, 2, 1, 5, 2.5, 1, 1, 2.5, 5, 2, 1, 5, 2.5, 1, 1, 2.5, 5, 2, 1, 5, 2.5, 1
//	};
	
	public static void run() {
		runSim(buyIn, _prizes, prizeWeights);
	}
	
	public static void runSim(double buyIn, double[] prizes, int[] _prizeWeights) {
		
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
			
			totalProduct += ( prizes[i] * _prizeWeights[i] ) / totalWeight;
		}
		
//		System.out.printf("Total Spent %.2f\n", totalSpent);
//		System.out.printf("Total Won %.2f\n", totalWon);
		
		int prizeWeightTotal = 0;
		int totalOverBuyIn = 0;
		int[] prizeWeightTotals = new int[_prizeWeights.length];
		for (int i = 0; i < _prizeWeights.length; i++) {
			prizeWeightTotal += _prizeWeights[i];
			prizeWeightTotals[i] = prizeWeightTotal;
			System.out.print(prizeWeightTotals[i] + " ");
			if (prizes[i] > buyIn) {
				totalOverBuyIn += _prizeWeights[i];
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
			int option = _prizeWeights.length;
			for (int j = 0; j < _prizeWeights.length; j++) {
				if (rng < prizeWeightTotals[j]) {
					option = j;
					break;
				}
			}
			if (option >= _prizeWeights.length) {
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
