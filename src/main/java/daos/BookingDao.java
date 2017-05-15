package daos;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer>{

}
