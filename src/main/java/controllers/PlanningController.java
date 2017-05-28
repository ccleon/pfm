package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BookingDao;
import daos.BungalowDao;
import entities.Booking;
import entities.Bungalow;
import wrappers.PlanningWrapper;

@Controller
public class PlanningController {
	
	private BookingController bookingController;
	
	private BungalowDao bungalowDao;
	
	@Autowired
	public void setBungalowgDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	private BookingDao bookingDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
		
	@Autowired
	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
	
	public Map<Integer, List<Integer>> getBookingsForPlanning(PlanningWrapper planningWrapper) {
		Calendar start = Calendar.getInstance();
		start.set(planningWrapper.getYear(), planningWrapper.getMonth()-1, start.getMinimum(Calendar.DAY_OF_MONTH));
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.HOUR_OF_DAY, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(planningWrapper.getYear(), planningWrapper.getMonth()-1, start.getActualMaximum(Calendar.DAY_OF_MONTH));
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR_OF_DAY, 0);
				
		List<Booking> listOfBookingInSelectedMonth = bookingDao.findByDatesBetween(start, end);

		return completePlanning(getDaysOfBookingsPerBungalow(listOfBookingInSelectedMonth, planningWrapper), start);
		//return getDaysOfBookingsPerBungalow(listOfBookingInSelectedMonth, planningWrapper);
	}
	
	public Calendar isArrivalInSelectedMonth(Calendar arrival, PlanningWrapper planningWrapper){
		boolean isArrivalInSelectedMonth = false;
		
		while(!isArrivalInSelectedMonth){
			if( (arrival.get(Calendar.MONTH)+1) == planningWrapper.getMonth()){
				isArrivalInSelectedMonth = true;
			}else{
				arrival.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return arrival;
	}
	
	public Calendar isDepartureInSelectedMonth(Calendar departure, PlanningWrapper planningWrapper){
		boolean isDepartureInSelectedMonth = false;
		
		while(!isDepartureInSelectedMonth){
			if( (departure.get(Calendar.MONTH)+1) == planningWrapper.getMonth()){
				isDepartureInSelectedMonth = true;
			}else{
				departure.add(Calendar.DAY_OF_MONTH, -1);
			}
		}
		return departure;
	}
	
	public List<Integer> getListOfDays (Booking booking, PlanningWrapper planningWrapper) {
		Calendar arrival = booking.getArrivalDate();
		Calendar departure = booking.getDepartureDate();
		List<Integer> bookingDays = new ArrayList<Integer>();
		List<Integer> fullMonthDays = new ArrayList<Integer>();
		
		arrival = isArrivalInSelectedMonth(arrival, planningWrapper);
		departure = isDepartureInSelectedMonth(departure, planningWrapper);

		String arrivalString = convertCalendarToString(arrival);
		String departureString = convertCalendarToString(departure);
		
		BigDecimal nights = bookingController.getTotalNights(arrivalString, departureString);

		for (int i=0; i<=nights.intValue() ;i++){
			bookingDays.add(arrival.get(Calendar.DAY_OF_MONTH)+i);
		}
		
		for(int i=1; i<= arrival.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
			if(bookingDays.contains(i)){
				fullMonthDays.add(1);
			}else{
				fullMonthDays.add(0);
			}
		}
		return fullMonthDays;
	}
	
	public String convertCalendarToString(Calendar date){
		String convertedDate= String.valueOf(date.get(Calendar.DAY_OF_MONTH)) 
				+ "/" + String.valueOf(date.get(Calendar.MONTH)+1)
				+ "/" + String.valueOf(date.get(Calendar.YEAR));
		
		return convertedDate;
	}
		
	
	public Map<Integer, List<Integer>> getDaysOfBookingsPerBungalow(List<Booking> listBookings, PlanningWrapper pw){
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		List<Bungalow> listB = bungalowDao.findAll();

		for(Booking b : listBookings){
			for(Bungalow bungalow : listB){
				if (bungalow.getNumber() == b.getBungalow().getNumber()){
					if(!map.containsKey(bungalow.getNumber())){
						map.put(bungalow.getNumber(), getListOfDays(b, pw));
					}else{
						List<Integer> otra = new ArrayList<Integer>();
						List<Integer> yaEstaba = map.get(bungalow.getNumber());
						List<Integer> laNueva = getListOfDays(b, pw);
						for (int i=1; i<=yaEstaba.size(); i++){
							if( (yaEstaba.get(i-1)==0) && (laNueva.get(i-1) == 0)){
								otra.add(0);
							}else{
								otra.add(1);
							}
						}
						map.put(bungalow.getNumber(), otra);
					}
				}
			}
		}
		return map;
	}
	
	public Map<Integer, List<Integer>> completePlanning (Map<Integer, List<Integer>> mapa, Calendar date){
		List<Bungalow> listB = bungalowDao.findAll();
		List<Integer> res = new ArrayList<Integer>();
		
		if (mapa.isEmpty()){
			for(Bungalow b : listB){
				res.clear();
				for (int i=1; i<=date.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
					res.add(0);
				}
			mapa.put(b.getNumber(), res);
			}
		}else{
			for(Bungalow b : listB){
				if(!mapa.containsKey(b.getNumber())){
					res.clear();
					for (int i=1; i<=date.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
						res.add(0);
					}
					mapa.put(b.getNumber(), res);
				}
			}
		}
		return mapa;
	}

}
