package com.techpp.modal;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	
	
	private String mobileNumber;
	private String licenceNO;
	private String status;
	private String curentLocationLong;
	private String curentLocationLat;
	private String groupCode;
	List<Rider> riders = new ArrayList<Rider>();
	VehicleDetails vehicle = new VehicleDetails() ;
	User user = new User() ;
	Ride rides = new Ride() ;
	
	public void setRiders(List<Rider> riders) {
		 this.riders = riders;
	}
	
	public List<Rider> getRiders() {
		return riders;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Ride getRides() {
		return rides;
	}
	public void setRides(Ride rides) {
		this.rides = rides;
	}
	public VehicleDetails getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleDetails vehicle) {
		this.vehicle = vehicle;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLicenceNO() {
		return licenceNO;
	}
	public void setLicenceNO(String licenceNO) {
		this.licenceNO = licenceNO;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurentLocationLong() {
		return curentLocationLong;
	}
	public void setCurentLocationLong(String curentLocationLong) {
		this.curentLocationLong = curentLocationLong;
	}
	public String getCurentLocationLat() {
		return curentLocationLat;
	}
	public void setCurentLocationLat(String curentLocationLat) {
		this.curentLocationLat = curentLocationLat;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public void addToRidersList(Rider rider) {
		riders.add(rider);
	}
	@Override
	public String toString() {
		return "Driver [mobileNumber=" + mobileNumber + ", licenceNO=" + licenceNO + ", status=" + status
				+ ", curentLocationLong=" + curentLocationLong + ", curentLocationLat=" + curentLocationLat
				+ ", groupCode=" + groupCode + ", vehicle=" + vehicle + ", user=" + user + ", rides=" + rides + "]";
	}
	
	

}
