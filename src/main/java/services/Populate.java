package services;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import daos.AuthorizationDao;
import daos.UserDao;
import entities.Authorization;
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

    @PostConstruct
    public void readAdmin() {
        adminUsername = environment.getProperty("admin.username");
        adminPassword = environment.getProperty("admin.password");
        createDefaultAdmin();
    }

    public void createDefaultAdmin() {
        User adminSaved = userDao.findByUsername(adminUsername);
        if (adminSaved == null) {
            User admin = new User(adminUsername, adminPassword);
            userDao.save(admin);
            authorizationDao.save(new Authorization(admin, Role.ADMIN));
        }
    }

}
