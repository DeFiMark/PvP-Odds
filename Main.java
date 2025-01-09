package main;

import java.util.Random;
//import main.Slots;

import gui.SlotsGUI;

public class Main {
	
	public static double calculateHouseFee(int range) {
		return range;
	}
	
	public static void getHouseEdge() {
		double permanentHouseInitial = 100000;
		
		double houseValue = permanentHouseInitial;
		double buyIn = 100;
		
//		int minRange = 5;
//		int maxRange = 1001;
		int iterations = 100000000;
		int numWins = 0;
		int numLosses = 0;
		
		double fee = 2.5;
		double houseFee = 1.5;
		double platformFees = 0;
		int minRange = 2;
		int maxRange = 6;
		
		double[] houseEdges = new double[maxRange - minRange];
		double[] platformEdges = new double[maxRange - minRange];
		double[] winRates = new double[maxRange - minRange];
		double[] fees = new double[maxRange - minRange];
		double[] houseFees = new double[maxRange - minRange];
		
		double desiredHouseEdge = fee - houseFee;
		double desiredPlatformEdge = 4.0;
		
//		long randomAverage = 0;
		
		Random r = new Random();
		
		int count = 0;
		int answer = 1;

		
			for (int j = minRange; j < maxRange; j++) {
//				fee = LOWEST_FEE + ( 4 * ( BASE_FEE-LOWEST_FEE ) / (j + 2));
//				fee = LOWEST_FEE + Math.min(BASE_FEE-LOWEST_FEE, (3 * (BASE_FEE-LOWEST_FEE) / ((double)j*1.5)));
//				houseFee = LOWEST_HOUSE_FEE + Math.min(BASE_HOUSE_FEE-LOWEST_HOUSE_FEE, (3 * (BASE_HOUSE_FEE-LOWEST_HOUSE_FEE) / ((double)j*1.5)));
				for (int i = 0; i < iterations; i++) {
					int rng = r.nextInt(Integer.MAX_VALUE);
//				randomAverage = ( ( randomAverage * i ) + rng ) / (i + 1);
					int guess = ( rng % j ) + 1;
					if (guess == answer) {
						double winningValue = ( buyIn * (j-1) * (100-fee) ) / 100;
						houseValue -= winningValue;
						numWins++;
//					System.out.println("WIN! | Range: " + range + " | Payout: " + winningValue);
					} else {
						double forHouse = ( buyIn * (100 - houseFee) ) / 100;
						double forPlatform = buyIn - forHouse;
						houseValue += forHouse;
						platformFees += forPlatform;
						numLosses++;
//					System.out.println("LOSS! | Guess: " + guess + " | Range: " + range);
					}
				}
				houseEdges[count] = ( 100 * (houseValue - permanentHouseInitial) ) / (buyIn * iterations);
				platformEdges[count] = ( 100 * platformFees ) / (buyIn * iterations);
				winRates[count] = (double)numWins / ((double)numLosses + (double)numWins);
				fees[count] = fee;
				houseFees[count] = houseFee;
				count++;
				
				houseValue = permanentHouseInitial;
				platformFees = 0;
				numWins = 0;
				numLosses = 0;
			}
			
			
	
		
		for (int i = 0; i < (maxRange - minRange); i++) {
			int currentRange = i + minRange;
			System.out.println("Range: " + currentRange);
			System.out.println("Fee: " + fees[i] + "%");
			System.out.println("Platform Fee: " + houseFees[i] + "%");
			System.out.printf("House Edge: %.4f%%\n", houseEdges[i]);
			System.out.printf("Platform Edge: %.4f%%\n", platformEdges[i]);
			System.out.printf("House Edge Difference: %.4f%%\n", (100 - (100 * ( houseEdges[i] / desiredHouseEdge ))));
			System.out.printf("Platform Edge Difference: %.4f%%\n", (100 - (100 * ( platformEdges[i] / desiredPlatformEdge ))));
			System.out.printf("Expected Difference: %.4f%%\n", (100.0 / (double)currentRange));
			if (i > 0) {
				System.out.printf("Improvement: %.5f%%\n", (100 * ( houseEdges[i] - houseEdges[i-1] ) / houseEdges[i-1]));
			}
			System.out.printf("Win Rate: %.6f\n", winRates[i]);
			System.out.printf("Expected Win Rate: %.6f\n\n\n", (1.00 / (double)currentRange));
		}
		
		//		System.out.println("House Initial: " + houseInitial + " | House Now: " + houseValue + " | Profit: " + ( houseValue - houseInitial ));
		//		System.out.println("Platform Fees: " + platformFees + " | Total Volume: " + (int)(buyIn * iterations));
		//		System.out.println("Platform Fee Per Game (avg): " + (platformFees / iterations));
		//		System.out.println("Platform Edge (per vol): " + (100 * platformFees / (buyIn * iterations)) + "%");
		//		System.out.println("House Fee Per Game (avg): " + ((houseValue - houseInitial) / iterations));
		//		System.out.println("House Edge (per vol): " + houseEdges[i] + "%");
		//		System.out.println("Num Wins: " + numWins);
		//		System.out.println("Num Losses: " + numLosses);
		//		System.out.println("Ratio Losses/Wins " + (double)numLosses/(double)numWins);
		//		System.out.println("Ratio Wins/Losses " + (double)numWins/(double)numLosses);
		//		System.out.println("Random Average vs Expected: " + randomAverage + " | " + Integer.MAX_VALUE/2);
		//		long diff = ( randomAverage - (Integer.MAX_VALUE/2));
		//		System.out.println("Random Average - Expected: " + diff);
		//		System.out.println("RNG Percent Deviation: " + Math.abs(( (double)diff)/(Integer.MAX_VALUE/2)) + "%");
	}
	
	
	
	public static void getNEWHouseEdge() {
		double permanentHouseInitial = 100000;
		
		double houseValue = permanentHouseInitial;
		double buyIn = 1;

		int iterations = 100000000;
		int numWins = 0;
		int numLosses = 0;
		
		double fee = 1.25;
		double payoutTax = 2.25;
		double platformFees = 0;
		
		int range = 10;
		int guess = 9;
		
		double winningValue = ( buyIn * range ) / guess;
		double winningValueAfterFees = winningValue - ( ( winningValue * payoutTax ) / 100 );
		double platformFee = ( buyIn * fee ) / 100;
		double forHouse = buyIn - platformFee;
		
		Random r = new Random();
		
				for (int i = 0; i < iterations; i++) {
					platformFees += platformFee;
					houseValue += forHouse;
					int rng = r.nextInt(Integer.MAX_VALUE);
					int answer = ( rng % range ) + 1;
					if (answer <= guess) {
						houseValue -= winningValueAfterFees;
						numWins++;
					} else {
						numLosses++;
					}
				}
		
			System.out.println("Range: " + range);
			System.out.println("Payout Tax: " + payoutTax + "%");
			System.out.println("Platform Fee: " + fee + "%");
			System.out.printf("Buy In: %.4f\n", buyIn);
			System.out.printf("Payout: %.4f\n", winningValueAfterFees);
//			System.out.println("Total Volume: " + ( buyIn * iterations) + "\n");
			System.out.printf("New House Value: %.2f\n", houseValue);
			double valueGained = houseValue - permanentHouseInitial;
			System.out.printf("House Profit: %.2f\n", valueGained);
			System.out.printf("House Edge: %.4f%%\n", 100 * valueGained / ( buyIn * iterations ));
			System.out.printf("Platform Fees: %.2f\n", platformFees);
			System.out.printf("Win Rate: %.4f%%\n", 100 * (double)numWins / ( (double)numWins + (double)numLosses ));
		}
	
	
	
	
	public static void runWTASim() {
		
		int initialPot = 0;// = 1000000;
		int initialDudesBalance = 1100000;
		int pot = initialPot;
		int costToBuyThePot = 1100000;
		int valueAddedToPot = 1000000;
		int dudesBalance = initialDudesBalance;
//		int rollOver = 0.1;
		
		int rollOverCut = 0;
		int winnings = 0;
		
		int iterations = 1000;
		for (int i = 0; i < iterations; i++) {
			
			System.out.printf("dudes balance: %,d\n", dudesBalance);
			dudesBalance -= costToBuyThePot;
			System.out.printf("dudes balance after buying: %,d\n", dudesBalance);
			pot += valueAddedToPot;
			System.out.printf("Pot after being filled: %,d\n", pot);

			rollOverCut = pot / 10;
			winnings = pot - rollOverCut;
			
			dudesBalance += winnings;
			pot -= winnings;
			System.out.printf("Pot after user winning: %,d\n", pot);
			
			System.out.printf("dudes balance after winning: %,d\n", dudesBalance);
			System.out.printf("Winnings: %,d\n", winnings);
			System.out.printf("Roll Over Cut: %,d\n\n\n", rollOverCut);
//			System.out.printf("Pot Balance: %,.2f\n\n", pot);
			
		}
		
		System.out.printf("\n\n\nDudes Initial Balance: %,d\n", initialDudesBalance);
		System.out.printf("dudes balance: %,d\n\n\n", dudesBalance);
		System.out.printf("Winnings: %,d\n", winnings);
		System.out.printf("Roll Over Cut: %,d\n\n\n", rollOverCut);
		
		System.out.printf("Initial Pot: %,d\n", initialPot);
		System.out.printf("Pot Balance: %,d\n\n", pot);
		
	}
	
	
	public static void getCuts() {
		
		int[] amounts = { 
				17, 
				10, 
				8, 
				8, 
				30, 
				2,
				12,
				4, 
				4, 
				5 
		};
		
		String[] names = {
				"Mark",
				"NFT",
				"Christian",
				"Paul",
				"Treasury",
				"Merc",
				"Profit Pool",
				"Day L",
				"Week L",
				"Partner L"
		};
		
		
		int[] parterAmounts = { 
				10, 
				20, 
				30,
				10,
				20,
				10
		};
		
		String[] partnerNames = {
				"Milord",
				"Duggs",
				"SurgeFund",
				"Giang",
				"Dappd",
				"Peter"
		};
		
		int sum = 0;
		int partnerSum = 0;
		for (int i = 0; i < amounts.length; i++) {
			sum += amounts[i];
		}
		for (int i = 0; i < parterAmounts.length; i++) {
			partnerSum += parterAmounts[i];
		}
		System.out.println("\n\nFee Receiver:\n");
		System.out.println("Name         Amount    Sum    Percentage"); // Header
		System.out.println("---------------------------------------"); // Divider

		for (int i = 0; i < amounts.length; i++) {
		    System.out.printf("%-12s %5d   %5d    %8.1f%%\n", names[i], amounts[i], sum, 100.0 * (double)amounts[i] / (double)sum);
		}
		System.out.println("\n\nProfit Pool:\n");
		System.out.println("Name         Amount    Sum    Percentage"); // Header
		System.out.println("---------------------------------------"); // Divider
		for (int i = 0; i < parterAmounts.length; i++) {
		    System.out.printf("%-12s %5d   %5d    %8.1f%%\n", partnerNames[i], parterAmounts[i], partnerSum, 100.0 * (double)parterAmounts[i] / (double)partnerSum);
		}
//		System.out.println("Total: " + sum);


	}
	
	public static void runSimGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SlotsGUI frame = new SlotsGUI();
                frame.setVisible(true); // make the frame visible
            }
        });
	}
	
	public static void main(String[] args) {
//		getHouseEdge();
//		runWTASim();
		
//		getNEWHouseEdge();
//		SlotsExperimental.run();
		
		
//		double tot = 0.5;
//		for (int i = 3; i <= 25; i++) {
//			tot *= (double)(i - 1) / (double)i;
//		}
//		System.out.println("Total: " + tot);
//		System.out.println("Expected: " + 1.0/25.0);
		
//		Plinko.run();
		
//		OptimalPlinko.run();
		
		
		// REAL SLOTS GAMES
//		BitcoinBonanza.run();
//		BTCBonanza2.run();
//		Slots.run();
//		SlotsExperimental.run();
//		PermaWinSlots.run();
		
		// END REAL SLOTS GAMES

		// Trying out the boost logic
//		BitcoinBonanzaBoost.run();
//		BluechipBoost.run();
//		MemecoinBoost.run();
		
		
		// NEWEST REAL SLOT GAMES FOR NEW UPDATE 10/21/2024 - Design Game UX Update
//		FinalBluechip.run();
//		FinalBonanza.run();
//		FinalMemecoin.run();
		
		SumProductSlots.run();
		
		
//		runSimGUI();
		
//		getCuts();
		
//		GoldRush.runMineSweep();
//		GoldRush.printMineSweepPayouts();
		
//		BitcoinBonanza.run();
//		SmallSlots.run();
//		SlotsExperimental.run();
//		Memecoinmadness.run(); // ADDING NEW NON-COMBOS TO IMPROVE UX
		
		
//		FortuneWheel.run();
		
//		shitcoinAlley.run();
//		FortuneWheel.run();
		
		
//		getNEWHouseEdge();

		
//		long inc = 50000000000000000l;
//		long tot = 0l;
//		System.out.print("[");
//		for (int i = 0; i < 100; i++) {
//			tot += inc;
//			System.out.print("\"" + tot + "\",");
//		}
//		System.out.print("]");
		
	}
	
}
