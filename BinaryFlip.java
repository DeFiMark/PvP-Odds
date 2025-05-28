package main;

import java.util.Random;

public class BinaryFlip {
	
	
	
	
	public static void run() {
		
		int iterations = 1_000_000_000;
		int chanceToWin = 63;
		double payout = 1.5;
		Random r = new Random();
		
		double bet = 1;
		double userBalance = 0;
		double houseBalance = 0;
		double houseBalanceNoFee = 0;
		double fee = 0.04;
		
		for (int i = 0; i < iterations; i++) {
			
			userBalance -= bet;
			houseBalance += ( bet * ( 1 - fee ) );
			houseBalanceNoFee += bet;
			
			int nextRandom = r.nextInt(Integer.MAX_VALUE) % 100;
			
			
			if (nextRandom < chanceToWin) {
				// user won
				double gamePayout = bet * payout;
				userBalance += gamePayout;
				houseBalance -= gamePayout;
				houseBalanceNoFee -= gamePayout;
			}
		}
		
		System.out.printf("User Balance %.2f\n", userBalance);
		System.out.printf("House Balance %.2f\n", houseBalance);
		System.out.printf("House Edge: %.4f\n", ( houseBalance ) / ( bet * iterations ));
		System.out.printf("House Edge No Fee: %.4f\n", ( houseBalanceNoFee ) / ( bet * iterations ));
		
	}
	
}
