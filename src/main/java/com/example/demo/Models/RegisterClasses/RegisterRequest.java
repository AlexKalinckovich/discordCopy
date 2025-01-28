package com.example.demo.Models.RegisterClasses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RegisterRequest {

    private final RegisterChecker registerChecker;

    public RegisterRequest(RegisterChecker registerChecker) {
        this.registerChecker = registerChecker;
    }

    @Setter
    @Getter
    private String username;
    @Getter
    @Setter
    private String password;

    public boolean isAlreadyRegistered(){
        return registerChecker.checkUserExists(username);
    }

}
