/*
 * Author - Anshima Gupta
 * Andrew ID - anshimag
 * This class deals with the information about
 * a car parked. There are methods to set and retrieve
 * information about the car.
 */

package ag.jsphdev.parking;

public class ParkedCar {
	
	private String model;
	private String make;
	private String color;
	private String licenseNumber;
	private int minutesSinceCarParked;
 	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the licenseNumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}
	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	/**
	 * @return the timeSinceCarParked
	 */
	public float getTimeSinceCarParked() {
		return minutesSinceCarParked;
	}
	/**
	 * @param timeSinceCarParked the timeSinceCarParked to set
	 */
	public void setTimeSinceCarParked(int timeSinceCarParked) {
		this.minutesSinceCarParked = timeSinceCarParked;
	}	
}
