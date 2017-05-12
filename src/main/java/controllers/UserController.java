package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.AuthorizationDao;
import daos.UserDao;
import entities.Authorization;
import entities.Role;
import entities.User;
import wrappers.UserCreateWrapper;
import wrappers.UserWrapper;

@Controller
public class UserController {

    private UserDao userDao;

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public boolean registration(UserCreateWrapper userCreateWrapper) {
        if (null == userDao.findByUsername(userCreateWrapper.getUsername())) {
            User user = new User(userCreateWrapper.getUsername(), userCreateWrapper.getPassword());
            userDao.save(user);
            authorizationDao.save(new Authorization(user, userCreateWrapper.getRole()));
            return true;
        } else {
            return false;
        }
    }
}
