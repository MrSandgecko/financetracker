package com.example.financetracker.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financetracker.Models.UserEnt;
import com.example.financetracker.Repositories.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void initializeUserAccounts() {

        if (userRepo.count() == 0) {
            userRepo.save(new UserEnt("admin", "admin"));
            userRepo.save(new UserEnt("secondUser", "123"));
            userRepo.save(new UserEnt("thirdUser", "123"));
        }
    }

    public boolean checkValidation(String username, String password) {
        Optional<UserEnt> userOptional = userRepo.findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public UserEnt getUserByUsername(String username) {
        Optional<UserEnt> userOptional = userRepo.findByUsername(username);
        return userOptional.orElse(null);
    }

}
