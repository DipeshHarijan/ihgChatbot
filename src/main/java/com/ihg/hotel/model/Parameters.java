package com.ihg.hotel.model;

import java.util.Date;

public class Parameters {

	private String city;
	private int adults;
	private int children;
	private Date checkIn;
	private Date checkOut;
	private int rooms;
	private String ratePreference;
	private RCNumber rcNumber;

	public RCNumber getRcNumber() {
		return rcNumber;
	}

	public void setRcNumber(RCNumber rcNumber) {
		this.rcNumber = rcNumber;
	}

	public String getRatePreference() {
		return ratePreference;
	}

	public void setRatePreference(String ratePreference) {
		this.ratePreference = ratePreference;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

}
