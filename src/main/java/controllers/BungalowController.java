package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BungalowDao;
import entities.Bungalow;
import wrappers.DateRangeAndIdBookingWrapper;
import wrappers.DateRangeWrapper;

@Controller
public class BungalowController {

	private BungalowDao bungalowDao;
	
	private BookingController bookingController;
		
	@Autowired
	public void setBungalowDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	@Autowired
	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
	
	public List<Bungalow> getAll(){
		List<Bungalow> bungalows = bungalowDao.findAll();
		return bungalows;
	}
	
	public List<Bungalow> getAvailabilityInDates(DateRangeWrapper dateRangeWrapper) {
		return bungalowDao.findAvailability(
				bookingController.createDate(dateRangeWrapper.getArrival()), 
				bookingController.createDate(dateRangeWrapper.getDeparture()));
	}
	
	public List<Bungalow> getAvailabilityInDatesForModify(DateRangeAndIdBookingWrapper dateRangeAndIdBookingWrapper) {
		System.out.println(dateRangeAndIdBookingWrapper.toString());
		return bungalowDao.findAvailabilityForModify(
				bookingController.createDate(dateRangeAndIdBookingWrapper.getArrival()), 
				bookingController.createDate(dateRangeAndIdBookingWrapper.getDeparture()),
				dateRangeAndIdBookingWrapper.getIdBooking());
	}
}
