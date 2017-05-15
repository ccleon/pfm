package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistUserFieldException;
import api.exceptions.InvalidUserFieldException;
import controllers.UserController;
import wrappers.UserCreateWrapper;

@RestController
@RequestMapping(Uris.USERS)
public class UserResource {

    private UserController userController;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void userRegistration(@RequestBody UserCreateWrapper userCreateWrapper) throws InvalidUserFieldException, AlreadyExistUserFieldException {
    	validateField(userCreateWrapper.getUsername(), "username");
        if (!this.userController.registration(userCreateWrapper)) {
            throw new AlreadyExistUserFieldException();
        }
    }
    
    private void validateField(String field, String msg) throws InvalidUserFieldException {
        if (field == null || field.isEmpty()) {
            throw new InvalidUserFieldException(msg);
        }
    }

}
