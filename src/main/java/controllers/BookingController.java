package controllers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.IncompleteModifyBookingException;
import daos.BookingDao;
import daos.ClientDao;
import daos.BungalowDao;
import entities.Booking;
import entities.Bungalow;
import wrappers.BookingCreateWrapper;
import wrappers.BookingModifyWrapper;
import wrappers.BookingSaveModifiedWrapper;
import wrappers.BookingSortedListWrapper;
import wrappers.DateRangeWrapper;
import wrappers.PruebaWrapper;

@Controller
public class BookingController {

	private BookingDao bookingDao;
	
	private ClientDao clientDao;
	
	private BungalowDao bungalowDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	@Autowired
	public void setBungalowDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	public List<Booking> getAll(){
		List<Booking> bookings = bookingDao.findAll();
		return bookings;
	}
	
	public Calendar createArrivalDate (String createArrivalDate){
		String delims = "/";
		String[] tokens = createArrivalDate.split(delims);
		
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[0]));
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.HOUR_OF_DAY, 14);
 
		return date;
	}
	
	public Calendar createDepartureDate (String createDepartureDate){
		String delims = "/";
		String[] tokens = createDepartureDate.split(delims);
		
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[0]));
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.HOUR_OF_DAY, 12);
 
		return date;
	}
	
	public BigDecimal getTotalNights(String arrivalDate, String departureDate){
		long arrivalMillis = createArrivalDate(arrivalDate).getTimeInMillis();
		long departureMillis = createArrivalDate(departureDate).getTimeInMillis();
		long diff = departureMillis - arrivalMillis;
		
		BigDecimal diffNights = new BigDecimal (diff / (24 * 60 * 60 * 1000));
		
		return diffNights;
	}
	
	public Booking createBooking(BookingCreateWrapper bookingCreateWrapper) {
		Bungalow bungalow = bungalowDao.findOne(bookingCreateWrapper.getIdBungalow());
		
		Booking booking = new Booking (
				bungalow, 
				clientDao.findOne(bookingCreateWrapper.getIdCliente()), 
				createArrivalDate(bookingCreateWrapper.getArrival()), 
				createDepartureDate(bookingCreateWrapper.getDeparture()), 
				(getTotalNights(bookingCreateWrapper.getArrival(), bookingCreateWrapper.getDeparture()).multiply(bungalow.getPricePerNight()))
			);

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
		
		BookingModifyWrapper booking = new BookingModifyWrapper(b.getId(), b.getBungalow(), b.getClient(), arrival, departure);
		
		return booking;
	}
	
	public void bookingModify (BookingSaveModifiedWrapper bookingSaveModifiedWrapper) throws IncompleteModifyBookingException {
		Booking booking = bookingDao.findOne(bookingSaveModifiedWrapper.getId());
		Bungalow bungalow = bungalowDao.findOne(bookingSaveModifiedWrapper.getIdBungalow());
		
		if((bungalow==null) || (bookingSaveModifiedWrapper.getArrival() == null) || (bookingSaveModifiedWrapper.getDeparture() == null)){
			throw new IncompleteModifyBookingException();
		}else{
			booking.setBungalow(bungalow);
			booking.setClient(clientDao.findOne(bookingSaveModifiedWrapper.getIdClient()));
			booking.setArrivalDate(createArrivalDate(bookingSaveModifiedWrapper.getArrival()));
			booking.setDepartureDate(createDepartureDate(bookingSaveModifiedWrapper.getDeparture()));
			booking.setTotalPrice((getTotalNights(bookingSaveModifiedWrapper.getArrival(), bookingSaveModifiedWrapper.getDeparture()).multiply(bungalow.getPricePerNight())));
			
			this.bookingDao.save(booking); 
		}
	}
	
	public List<Booking> getBookingByDateRange(DateRangeWrapper dateRangeWrapper) throws IncompleteModifyBookingException {
		if ((dateRangeWrapper.getArrival() == null) || (dateRangeWrapper.getDeparture() == null)){
			throw new IncompleteModifyBookingException();
		}else{
			return bookingDao.findByDatesBetween(
					createArrivalDate(dateRangeWrapper.getArrival()), 
					createDepartureDate(dateRangeWrapper.getDeparture()));
		}
	}

	public List<Booking> sortBy(BookingSortedListWrapper parameter) {
		if(parameter.getParameter().equals("bungalow")){
			return bookingDao.findAllByOrderByBungalowAsc();
		}else if(parameter.getParameter().equals("entrada")){
			return bookingDao.findAllByOrderByArrivalDateAsc();
		}else if(parameter.getParameter().equals("cliente")){
			return bookingDao.findAllByOrderByClientAsc();
		}else{
			return bookingDao.findAllByOrderByIdDesc();
		}
	}
	
	/*public List<Booking> getBookingsByC(PruebaWrapper p){
		System.out.println(p.toString());
		return bookingDao.findAll();
	}*/
}
