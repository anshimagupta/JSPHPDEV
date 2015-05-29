/*
 * Author - Anshima Gupta
 * Andrew ID - anshimag
 * This class has an attribute which determines 
 * the side of the coin facing up on a toss.
 * 
 */
package ag.jsphdev.coin;

public class Coin {

	public enum SideUp {
		HEADS, TAILS
	};

	private static final double PROB_HEAD = 0.5;
	SideUp sideUp;

	public Coin() {
		toss();
	}

	/**
	 * This method randomly assigns the coin a head or tail
	 */

	public void toss() {

		if (Math.random() < PROB_HEAD) {
			setSideUp(SideUp.HEADS);
		} else {
			setSideUp(SideUp.TAILS);
		}
	}

	/**
	 * @return the sideUp
	 */
	public SideUp getSideUp() {
		return sideUp;
	}

	/**
	 * @param sideUp
	 *            the sideUp to set
	 */
	public void setSideUp(SideUp sideUp) {
		this.sideUp = sideUp;
	}
}
