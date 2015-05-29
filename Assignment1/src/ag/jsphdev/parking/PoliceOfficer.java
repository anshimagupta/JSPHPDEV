/*
 * Author - Anshima Gupta
 * Andrew ID - anshimag
 * This class contains the Police Officer details
 * and methods which check if the parking ticket needs
 * to be assigned or not.
 * 
 */

package ag.jsphdev.parking;

public class PoliceOfficer {
	
	private String name;
	private String badgeNumber;
	private double additionalHours;
	
	
	public boolean isParkingTimeExpired(ParkedCar parkedCar, ParkingMeter parkingMeter){
		
		if(parkedCar.getTimeSinceCarParked() > parkingMeter.getParkingMinutesPurchased())
			return true;
		else 
			return false;
		
	}
	

	public ParkingTicket issueTicket(ParkedCar parkedCar, ParkingMeter parkingMeter){

		additionalHours = Math.ceil((parkedCar.getTimeSinceCarParked() - parkingMeter.getParkingMinutesPurchased()
				)/60.00);
		ParkingTicket newTicket = new ParkingTicket(parkedCar, additionalHours, this);
		return newTicket;
		}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the badgeNumber
	 */
	public String getBadgeNumber() {
		return badgeNumber;
	}



	/**
	 * @param badgeNumber the badgeNumber to set
	 */
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
}
