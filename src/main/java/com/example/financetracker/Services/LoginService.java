package com.example.financetracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {

        this.userService = userService;

        userService.initializeUserAccounts();

    }

    // /**********************************************
    // *Check if password is correct
    // ***********************************************/
    public boolean checkCreds(String username, String password) {

        if (userService.checkValidation(username, password)) {
            return true;
        } else {
            return false;
        }

    }
}
