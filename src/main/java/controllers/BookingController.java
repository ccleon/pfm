package controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BookingDao;
import daos.ClientDao;
import entities.Booking;
import entities.Client;
import wrappers.BookingCreateWrapper;
import wrappers.BookingWrapper;

@Controller
public class BookingController {

	private BookingDao bookingDao;
	
	private ClientDao clientDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	public List<Booking> getAll(){
		List<Booking> bookings = bookingDao.findAll();
		return bookings;
	}
	

	public Booking createBooking(BookingCreateWrapper bookingCreateWrapper) {
		Calendar arrival = Calendar.getInstance();
		Calendar departure = Calendar.getInstance();
		Client client = clientDao.findOne(bookingCreateWrapper.getIdcliente());

		Booking booking = new Booking (bookingCreateWrapper.getBungalow(), client, arrival, departure);

		return bookingDao.save(booking);
	}
	
	public Booking getBookingById(int id){
		return bookingDao.findOne(id);
	}
	
	public void bookingModify (BookingWrapper bookingWrapper) {
		Booking booking = bookingDao.findOne(bookingWrapper.getId());
		Client client = clientDao.findOne(bookingWrapper.getClientId());
		
		booking.setBungalow(bookingWrapper.getBungalow());
		booking.setClient(client);
		booking.setArrivalDate(bookingWrapper.getArrivalDate());
		booking.setDepartureDate(bookingWrapper.getDepartureDate());
		
		this.bookingDao.save(booking);
	}
	
	/*public long getBookingDays(BookingCreateWrapper bookingCreateWrapper){
	Calendar arrival = bookingCreateWrapper.getArrivalDate();
	Calendar departure = bookingCreateWrapper.getDepartureDate();
	
	long arrivalMillis = arrival.getTimeInMillis();
	long departureMillis = departure.getTimeInMillis();
	
	long diff = departureMillis - arrivalMillis;
	
	long diffDays = diff / (24 * 60 * 60 * 1000);
	
	return diffDays;
}*/	
}
