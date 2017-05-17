package services;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import daos.AuthorizationDao;
import daos.BookingDao;
import daos.BungalowDao;
import daos.ClientDao;
import daos.UserDao;
import entities.Authorization;
import entities.Booking;
import entities.Bungalow;
import entities.Client;
import entities.Role;
import entities.User;

@Service
@Transactional
@PropertySource(ResourceNames.PROPERTIES)
public class Populate {

    private String adminUsername;

    private String adminPassword;

    @Autowired
    private Environment environment;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationDao authorizationDao;
    
    @Autowired
    private ClientDao clientDao;
    
    @Autowired
    private BookingDao bookingDao;
    
    @Autowired
    private BungalowDao bungalowDao;
    
    @PostConstruct
    public void readAdmin() {
        adminUsername = environment.getProperty("admin.username");
        adminPassword = environment.getProperty("admin.password");
        createDefaultAdmin();
        populate();
    }

    public void createDefaultAdmin() {
        User adminSaved = userDao.findByUsername(adminUsername);
        if (adminSaved == null) {
            User admin = new User(adminUsername, adminPassword);
            userDao.save(admin);
            authorizationDao.save(new Authorization(admin, Role.ADMIN));
        }
    }
    
    public void populate(){
    	//CLIENTE
    	Client client1 = new Client("Cliente", "Prueba", "123", "456");
    	clientDao.save(client1);
    	Client client2 = new Client("Pepe", "Sanchez", "789", "123");
    	clientDao.save(client2);
    	Client client3 = new Client("Corina", "Cabrera", "567", "876");
    	clientDao.save(client3);
    	
    	//BUNGALOW
    	Bungalow bungalow = new Bungalow(20, new BigDecimal(85.00));
    	bungalowDao.save(bungalow);
    	Bungalow bungalow2 = new Bungalow(12, new BigDecimal(85.00));
    	bungalowDao.save(bungalow2);
    	Bungalow bungalow3 = new Bungalow(19, new BigDecimal(85.00));
    	bungalowDao.save(bungalow3);
    	
    	//BOOKING
    	Booking booking = new Booking(bungalow, client2, Calendar.getInstance(), Calendar.getInstance(), new BigDecimal(85.00));
    	bookingDao.save(booking);
    }

}
