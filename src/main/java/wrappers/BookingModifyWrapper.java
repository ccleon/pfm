package wrappers;

import java.util.Calendar;
import java.util.Date;

import entities.Client;

public class BookingModifyWrapper {
	
	private String bungalow; 
	
	private Client client;
	
	private String arrival;
	
	private String departure;
	
	private int id;
	
	public BookingModifyWrapper() {
	
	}

	public BookingModifyWrapper(int id, String bungalow, Client client, String arrival, String departure) {
		this.id = id;
		this.bungalow = bungalow;
		this.client = client;
		this.arrival = arrival;
		this.departure = departure;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		return "BookingCreateWrapper [id=" + id + ", bungalow=" + bungalow + ", client=" + client + ", arrival=" + arrival
				+ ", departure=" + departure + "]";
	}
}
