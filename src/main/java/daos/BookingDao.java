package daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer>{

	@Query("SELECT b FROM Booking b WHERE ("
			+ " (?1 between b.arrivalDate and b.departureDate OR ?2 between b.arrivalDate and b.departureDate) OR " 
			+ "(b.arrivalDate between ?1 and ?2 OR b.departureDate between ?1 and ?2))")
	List<Booking> findByDatesBetween(Calendar start, Calendar end);

}
