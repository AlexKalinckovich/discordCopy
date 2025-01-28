package com.example.demo.Services;

import com.example.demo.Models.AuthorizationClasses.LoginChecker;
import com.example.demo.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class AuthorizationService {
    private final LoginChecker loginChecker;
    private boolean isAuthorized = false;

    public AuthorizationService(LoginChecker loginChecker) {
        this.loginChecker = loginChecker;
    }


    public boolean authorize(final String username, final String password,final Model model) {
        isAuthorized = true;
        loginChecker.checkLogin(username, password);
        if(!loginChecker.checkLogin(username, password)) {
            model.addAttribute("error", "Пользователь уже зарегестрирован");
            isAuthorized = false;
        }
        return isAuthorized;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(final boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public Optional<User> getCurrentUser(){
        Optional<User> user = Optional.empty();
        if(isAuthorized){
             user = loginChecker.getUser();
        }
        return user;
    }
}
