package ag.jsphdev.parking;

public class Simulation {
	
	public void start(){
		ParkedCar carDetails = new ParkedCar();
		PoliceOfficer policeOfficer = new PoliceOfficer();
		ParkingMeter parkingMeter = new ParkingMeter();
		
		carDetails.setMake("BMW");
		carDetails.setModel("2015");
		carDetails.setColor("Grey");
		carDetails.setLicenseNumber("CAL01234");
		
		policeOfficer.setName("Popeye Doyle");
		policeOfficer.setBadgeNumber("FC1971");
		
		//ParkedVehicle is with in, out of and just in the parking time purchased.
		
		System.out.println("Testing: ParkedVehicle is within the parking time purchased");
		System.out.println("parked time = 30min and purchased time = 60min");
		carDetails.setTimeSinceCarParked(30);
		parkingMeter.setParkingMinutesPurchased(60);
		
		if (policeOfficer.isParkingTimeExpired(carDetails, parkingMeter)){
			ParkingTicket parkingTicket1 = policeOfficer.issueTicket(carDetails, parkingMeter);
			parkingTicket1.printTicket();
		} else{
			System.out.println("No Parking ticket issued. Parking is within the minutes Purchased");	
		}
		System.out.println();
		
		System.out.println("Testing: ParkedVehicle is outside the parking time purchased");
		System.out.println("parked time = 180 min and purchased time = 60 min");
		carDetails.setTimeSinceCarParked(180);
		parkingMeter.setParkingMinutesPurchased(60);
		
		if (policeOfficer.isParkingTimeExpired(carDetails, parkingMeter)){
			ParkingTicket parkingTicket2 = policeOfficer.issueTicket(carDetails, parkingMeter);
			parkingTicket2.printTicket();
		} else{
			System.out.println("No Parking ticket issued. Parking is within the minutes Purchased");
		}
		System.out.println();
		
		
		System.out.println("Testing: ParkedVehicle is just within the parking time purchased");
		System.out.println("parked time = 120 min and purchased time = 120 min");
		carDetails.setTimeSinceCarParked(120);
		parkingMeter.setParkingMinutesPurchased(120);
		
		if (policeOfficer.isParkingTimeExpired(carDetails, parkingMeter)){
			ParkingTicket parkingTicket3 = policeOfficer.issueTicket(carDetails, parkingMeter);
			parkingTicket3.printTicket();
		} else{
			System.out.println("No Parking ticket issued. Parking is within the minutes Purchased");
		}
		System.out.println();
		
		//Ticketing under 1 hour and more than 1 hour test cases
		

		System.out.println("Testing: ParkedVehicle is parked for exactly 3 hours more");
		System.out.println("parked time = 300 min and purchased time = 120 min");
		carDetails.setTimeSinceCarParked(300);
		parkingMeter.setParkingMinutesPurchased(120);
		
		if (policeOfficer.isParkingTimeExpired(carDetails, parkingMeter)){
			ParkingTicket parkingTicket4 = policeOfficer.issueTicket(carDetails, parkingMeter);
			parkingTicket4.printTicket();
		} else{
			System.out.println("No Parking ticket issued. Parking is within the minutes Purchased");
		}
		System.out.println();
		
		System.out.println("Testing: ParkedVehicle is parked for 1.5 hours more");
		System.out.println("parked time = 300 min and purchased time = 120 min");
		carDetails.setTimeSinceCarParked(210);
		parkingMeter.setParkingMinutesPurchased(120);
		
		if (policeOfficer.isParkingTimeExpired(carDetails, parkingMeter)){
			ParkingTicket parkingTicket4 = policeOfficer.issueTicket(carDetails, parkingMeter);
			parkingTicket4.printTicket();
		} else{
			System.out.println("No Parking ticket issued. Parking is within the minutes Purchased");
		}
		System.out.println();
		
	}

}
