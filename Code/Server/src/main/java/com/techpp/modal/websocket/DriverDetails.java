package com.techpp.modal.websocket;

public class DriverDetails {
	
	private String currentLat;
	private String currentLong;
	private String seatsAvailable;
	
	
	public DriverDetails(String currentLat, String currentLong, String seatsAvailable) {
		super();
		this.currentLat = currentLat;
		this.currentLong = currentLong;
		this.seatsAvailable = seatsAvailable;
	}
	public String getCurrentLat() {
		return currentLat;
	}
	public void setCurrentLat(String currentLat) {
		this.currentLat = currentLat;
	}
	public String getCurrentLong() {
		return currentLong;
	}
	public void setCurrentLong(String currentLong) {
		this.currentLong = currentLong;
	}
	public String getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(String seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	
	
	
	

}
