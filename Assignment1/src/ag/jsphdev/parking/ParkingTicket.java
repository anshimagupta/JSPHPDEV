/*
 * Author - Anshima Gupta
 * Andrew ID - anshimag
 * This class prints the ticket with all
 * the details like fine, car type and Police officer
 * 
 */
package ag.jsphdev.parking;

public class ParkingTicket {
	
	private String parkedCarMake;
	private String parkedCarModel;
	private String parkedCarColor;
	private String parkedCarLicenseNumber;
	private double fine = 0.00;
	private String policeOfficerName;
	private String policeOfficerBadgeNumber;
	
	public ParkingTicket(ParkedCar carDetails, double additionalHours, PoliceOfficer policeOfficer) {
		
		parkedCarMake = carDetails.getMake();
		parkedCarModel = carDetails.getModel();
		parkedCarColor = carDetails.getColor();
		parkedCarLicenseNumber = carDetails.getLicenseNumber();
		fine = 25.00 + 10.00*(additionalHours - 1);
		policeOfficerName = policeOfficer.getName();
		policeOfficerBadgeNumber = policeOfficer.getBadgeNumber();
		
	}
	
	public void printTicket(){
		
		System.out.println("#####PARKING TICKET#####");
		System.out.println("Car Make: " + parkedCarMake);
		System.out.println("Car Model: " + parkedCarModel);
		System.out.println("Car Color: " + parkedCarColor);
		System.out.println("Car License Number: " + parkedCarLicenseNumber);
		System.out.println("Fine due: " + fine + "$");
		System.out.println("Issued by: " + policeOfficerName);
		System.out.println("Police Officer's Badge Number: " + policeOfficerBadgeNumber);
		System.out.println("#####PARKING TICKET #####");

	}

}
