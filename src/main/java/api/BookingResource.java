package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundClientIdException;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.BookingController;
import entities.Booking;
import entities.Client;
import wrappers.BookingCreateWrapper;
import wrappers.BookingWrapper;
import wrappers.ClientWrapper;

@RestController
@RequestMapping(Uris.BOOKINGS)
public class BookingResource {

	private BookingController bookingController;
	
	@Autowired
	public void setBookingController(BookingController bookingController){
		this.bookingController = bookingController;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Booking> listBookings(){
		return bookingController.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public Booking createBooking(@RequestBody BookingCreateWrapper bookingCreateWrapper) throws NotFoundClientIdException{
    	/*if (!this.bookingController.validateIdClient(bookingCreateWrapper)){
    		throw new NotFoundClientIdException();
    	}else{*/
    		return bookingController.createBooking(bookingCreateWrapper);
    	//}
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public void modifyBooking (@RequestBody BookingWrapper bookingWrapper) {
    	this.bookingController.bookingModify(bookingWrapper);
    }
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.GET)  
    public Booking getBookingById(@PathVariable(value = "id") int id){
        return bookingController.getBookingById(id);
    }
}
