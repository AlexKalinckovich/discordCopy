package com.example.demo;

import com.example.demo.Models.AuthorizationClasses.LoginChecker;
import com.example.demo.Services.AuthorizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AuthorizationTests {


    @Test
    @DisplayName("Username is valid and password is valid")
    public void allGood(){

        LoginChecker loginChecker = mock(LoginChecker.class);
        AuthorizationService authorizationService = new AuthorizationService(loginChecker);

        String username = "admin";
        String password = "admin";
        given(loginChecker.checkLogin(username, password)).willReturn(true);
        boolean result = authorizationService.authorize(username,password,null);
        assertTrue(result);
    }
}
