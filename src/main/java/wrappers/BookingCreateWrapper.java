package wrappers;

import java.util.Calendar;

public class BookingCreateWrapper {
	
	private int idcliente;
	
	private String bungalow; 
	
	private Calendar arrivalDate;
	
	private Calendar departureDate;
	
	public BookingCreateWrapper() {
	}

	public BookingCreateWrapper(int idcliente, String bungalow, Calendar arrivalDate, Calendar departureDate) {
		this.idcliente = idcliente;
		this.bungalow = bungalow;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}
		

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getBungalow() {
		return bungalow;
	}

	public void setBungalow(String bungalow) {
		this.bungalow = bungalow;
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
		return "BookingCreateWrapper [bungalow=" + bungalow + ", idcliente=" + idcliente + ", arrivalDate=" + arrivalDate
				+ ", departureDate=" + departureDate + "]";
	}
}
