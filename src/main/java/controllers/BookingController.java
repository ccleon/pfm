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
import wrappers.BookingModifyWrapper;
import wrappers.BookingModifyWrapper2;
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
	
	public Calendar createDate (String createDate){
		String delims = "/";
		String[] tokens = createDate.split(delims);
		
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(tokens[2])-1, Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[0]));

		return date;
	}
	
	public long getBookingDays(BookingCreateWrapper bookingCreateWrapper){
		Calendar arrival = createDate(bookingCreateWrapper.getArrival());
		Calendar departure = createDate(bookingCreateWrapper.getDeparture());
		
		long arrivalMillis = arrival.getTimeInMillis();
		long departureMillis = departure.getTimeInMillis();
		
		long diff = departureMillis - arrivalMillis;
		
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffDays*85;
	}
	
	public Booking createBooking(BookingCreateWrapper bookingCreateWrapper) {
		Calendar arrival = createDate(bookingCreateWrapper.getArrival());
		Calendar departure = createDate(bookingCreateWrapper.getDeparture());

		Client client = clientDao.findOne(bookingCreateWrapper.getIdcliente());
		Booking booking = new Booking (bookingCreateWrapper.getBungalow(), client, arrival, departure, getBookingDays(bookingCreateWrapper));
		System.out.println(booking.toString());
		return bookingDao.save(booking);
	}
	
	public BookingModifyWrapper getBookingById(int id){
		Booking b = bookingDao.findOne(id);
		String arrival= String.valueOf(b.getArrivalDate().get(Calendar.DAY_OF_MONTH)) 
				+ "/" + String.valueOf(b.getArrivalDate().get(Calendar.MONTH)+1)
				+ "/" + String.valueOf(b.getArrivalDate().get(Calendar.YEAR));
		
		String departure= String.valueOf(b.getDepartureDate().get(Calendar.DAY_OF_MONTH)) 
				+ "/" + String.valueOf(b.getDepartureDate().get(Calendar.MONTH)+1)
				+ "/" + String.valueOf(b.getDepartureDate().get(Calendar.YEAR));
		System.out.println(b.toString());
		
		BookingModifyWrapper booking = new BookingModifyWrapper(b.getId(), b.getBungalow(), b.getClient(), arrival, departure);
		
		return booking;
	}
	
	public void bookingModify (BookingModifyWrapper2 bookingWrapper) {
		Booking booking = bookingDao.findOne(bookingWrapper.getId());
		Client c = clientDao.findOne(bookingWrapper.getIdclient());
		
		booking.setBungalow(bookingWrapper.getBungalow());
		booking.setClient(c);
		booking.setArrivalDate(createDate(bookingWrapper.getArrival()));
		booking.setDepartureDate(createDate(bookingWrapper.getDeparture()));
		
		this.bookingDao.save(booking);
	}
}
