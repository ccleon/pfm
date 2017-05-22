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
    	Bungalow bungalow = new Bungalow(1, new BigDecimal(85.00));
    	bungalowDao.save(bungalow);
    	Bungalow bungalow2 = new Bungalow(2, new BigDecimal(85.00));
    	bungalowDao.save(bungalow2);
    	Bungalow bungalow3 = new Bungalow(3, new BigDecimal(85.00));
    	bungalowDao.save(bungalow3);
    	Bungalow bungalow4 = new Bungalow(4, new BigDecimal(85.00));
    	bungalowDao.save(bungalow4);
    	Bungalow bungalow5 = new Bungalow(5, new BigDecimal(85.00));
    	bungalowDao.save(bungalow5);
    	Bungalow bungalow6 = new Bungalow(6, new BigDecimal(85.00));
    	bungalowDao.save(bungalow6);
    	Bungalow bungalow7 = new Bungalow(7, new BigDecimal(85.00));
    	bungalowDao.save(bungalow7);
    	Bungalow bungalow8 = new Bungalow(8, new BigDecimal(85.00));
    	bungalowDao.save(bungalow8);
    	Bungalow bungalow9 = new Bungalow(9, new BigDecimal(85.00));
    	bungalowDao.save(bungalow9);
    	Bungalow bungalow10 = new Bungalow(10, new BigDecimal(85.00));
    	bungalowDao.save(bungalow10);
    	Bungalow bungalow11 = new Bungalow(11, new BigDecimal(85.00));
    	bungalowDao.save(bungalow11);
    	Bungalow bungalow12 = new Bungalow(12, new BigDecimal(85.00));
    	bungalowDao.save(bungalow12);
    	Bungalow bungalow13 = new Bungalow(13, new BigDecimal(85.00));
    	bungalowDao.save(bungalow13);
    	Bungalow bungalow14 = new Bungalow(14, new BigDecimal(85.00));
    	bungalowDao.save(bungalow14);
    	
    	
    	
    	Calendar arrival = Calendar.getInstance();
    	arrival.set(2017, 4, 1);
    	arrival.set(Calendar.MILLISECOND, 0);
    	arrival.set(Calendar.SECOND, 0);
    	arrival.set(Calendar.MINUTE, 0);
    	arrival.set(Calendar.HOUR_OF_DAY, 0);
    	Calendar departure = Calendar.getInstance();
    	departure.set(2017, 4, 7);
    	departure.set(Calendar.MILLISECOND, 0);
    	departure.set(Calendar.SECOND, 0);
    	departure.set(Calendar.MINUTE, 0);
    	departure.set(Calendar.HOUR_OF_DAY, 0);
    	Calendar arrival2 = Calendar.getInstance();
    	arrival2.set(2017, 4, 6);
    	arrival2.set(Calendar.MILLISECOND, 0);
    	arrival2.set(Calendar.SECOND, 0);
    	arrival2.set(Calendar.MINUTE, 0);
    	arrival2.set(Calendar.HOUR_OF_DAY, 0);
    	Calendar departure2 = Calendar.getInstance();
    	departure2.set(2017, 4,14);
    	departure2.set(Calendar.MILLISECOND, 0);
    	departure2.set(Calendar.SECOND, 0);
    	departure2.set(Calendar.MINUTE, 0);
    	departure2.set(Calendar.HOUR_OF_DAY, 0);
    	Calendar arrival3 = Calendar.getInstance();
    	arrival3.set(2017, 4, 14);
    	arrival3.set(Calendar.MILLISECOND, 0);
    	arrival3.set(Calendar.SECOND, 0);
    	arrival3.set(Calendar.MINUTE, 0);
    	arrival3.set(Calendar.HOUR_OF_DAY, 0);
    	Calendar departure3 = Calendar.getInstance();
    	departure3.set(2017, 4, 20);
    	departure3.set(Calendar.MILLISECOND, 0);
    	departure3.set(Calendar.SECOND, 0);
    	departure3.set(Calendar.MINUTE, 0);
    	departure3.set(Calendar.HOUR_OF_DAY, 0);

    	
    	//BOOKING
    	Booking booking = new Booking(bungalow, client1, arrival, departure, new BigDecimal(85.00));
    	bookingDao.save(booking);
    	Booking booking2 = new Booking(bungalow2, client2, arrival2, departure2, new BigDecimal(85.00));
    	bookingDao.save(booking2);
    	Booking booking3 = new Booking(bungalow3, client3, arrival3, departure3, new BigDecimal(85.00));
    	bookingDao.save(booking3);
    	Booking booking4 = new Booking(bungalow4, client2, arrival2, departure3, new BigDecimal(85.00));
    	bookingDao.save(booking4);
    	Booking booking5 = new Booking(bungalow6, client3, arrival, departure2, new BigDecimal(85.00));
    	bookingDao.save(booking5);
    	Booking booking6 = new Booking(bungalow8, client2, arrival, departure3, new BigDecimal(85.00));
    	bookingDao.save(booking6);
    }

}
