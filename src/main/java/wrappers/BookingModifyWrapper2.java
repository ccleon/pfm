package wrappers;

import java.util.Calendar;
import java.util.Date;

import entities.Client;

public class BookingModifyWrapper2 {
	
	private String bungalow; 
	
	private int idclient;
	
	private String arrival;
	
	private String departure;
	
	private int id;
	
	public BookingModifyWrapper2() {
	
	}

	public BookingModifyWrapper2(int id, String bungalow, int idclient, String arrival, String departure) {
		this.id = id;
		this.bungalow = bungalow;
		this.idclient = idclient;
		this.arrival = arrival;
		this.departure = departure;
	}
	
	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBungalow() {
		return bungalow;
	}

	public void setBungalow(String bungalow) {
		this.bungalow = bungalow;
	}
	
	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "BookingCreateWrapper [id=" + id + ", bungalow=" + bungalow + ", idclient=" + idclient + ", arrival=" + arrival
				+ ", departure=" + departure + "]";
	}
}
