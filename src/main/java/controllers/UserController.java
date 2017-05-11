package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.AuthorizationDao;
import daos.UserDao;
import entities.Authorization;
import entities.Role;
import entities.User;
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

    public boolean registration(UserWrapper userWrapper, Role role) {
        if (null == userDao.findByUsername(userWrapper.getUsername())) {
            User user = new User(userWrapper.getUsername(), userWrapper.getPassword());
            userDao.save(user);
            authorizationDao.save(new Authorization(user, role));
            return true;
        } else {
            return false;
        }
    }
}
