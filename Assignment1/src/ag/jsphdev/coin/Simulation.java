/*
 * Author - Anshima Gupta
 * Andrew ID - anshimag
 * This class is used to simulate the coin toss
 * behavior 20 times.
 * 
 */

package ag.jsphdev.coin;

import ag.jsphdev.coin.Coin.SideUp;

public class Simulation {

	private static final int totalTossCount = 20;

	public void start() {
		int headCount = 0;
		int tailCount = 0;
		Coin flipCoin = new Coin();
		
		System.out.println("The side which is initially facing up is: "
				+ flipCoin.getSideUp());

		for (int i = 0; i < totalTossCount; i++) {
			flipCoin.toss();
			System.out.println("The side which is up in toss number " + (i + 1)
					+ ":" + flipCoin.getSideUp());

			if (flipCoin.getSideUp() == SideUp.HEADS){
				headCount++;
			}		
			else{
				tailCount++;
			}
		}
		
		System.out.println("Total number of Heads in the toss: " + headCount);
		System.out.println("Total number of Tails in the toss: " + tailCount);

	}

}
