package daos;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Bungalow;

public interface BungalowDao extends JpaRepository<Bungalow, Integer>{

}
