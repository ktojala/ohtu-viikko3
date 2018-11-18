package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {
    
    private static final int USERNAME_LENGTH_MIN = 3;
    private static final int PASSWORD_LENGTH_MIN = 8;

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if (username.length() < USERNAME_LENGTH_MIN || password.length() < PASSWORD_LENGTH_MIN) {
            return true;
        }
        
        if (!username.matches("^[a-z]*$")) {
            return true;
        }
        
        if (password.matches("^[a-z]*$")) {
            return true;
        }

        return false;
    }
}
