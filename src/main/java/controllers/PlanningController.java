package controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BookingDao;
import entities.Booking;
import wrappers.PlanningWrapper;

@Controller
public class PlanningController {
	
	private BookingController bookingController;
	
	private BookingDao bookingDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
		
	@Autowired
	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
	
	public List<Booking> getBookingsByMonth(PlanningWrapper planningWrapper) {
		Calendar start = Calendar.getInstance();
		start.set(planningWrapper.getYear(), planningWrapper.getMonth()-1, start.getMinimum(Calendar.DAY_OF_MONTH));
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.HOUR_OF_DAY, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(planningWrapper.getYear(), planningWrapper.getMonth()-1, end.getActualMaximum(Calendar.DAY_OF_MONTH));
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR_OF_DAY, 0);
		
		return bookingDao.findByDatesBetween(start, end);
	}
}
