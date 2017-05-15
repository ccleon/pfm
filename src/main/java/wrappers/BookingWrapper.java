package wrappers;

import java.util.Calendar;

public class BookingWrapper {
	
	private String bungalow; 
	
	private int idClient;
	
	private Calendar arrivalDate;
	
	private Calendar departureDate;
	
	private int id;
	
	public BookingWrapper() {
	
	}

	public BookingWrapper(int id, String bungalow, int idClient, Calendar arrivalDate, Calendar departureDate) {
		this.id = id;
		this.bungalow = bungalow;
		this.idClient = idClient;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
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

	public int getClientId() {
		return idClient;
	}

	public void setClientId(int client) {
		this.idClient = client;
	}

	public Calendar getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Calendar arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Calendar getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Calendar departureDate) {
		this.departureDate = departureDate;
	}

	@Override
	public String toString() {
		return "BookingCreateWrapper [id=" + id + ", bungalow=" + bungalow + ", idClient=" + idClient + ", arrivalDate=" + arrivalDate
				+ ", departureDate=" + departureDate + "]";
	}
}
