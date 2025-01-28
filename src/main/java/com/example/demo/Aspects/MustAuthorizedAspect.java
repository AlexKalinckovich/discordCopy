package com.example.demo.Aspects;

import com.example.demo.Controllers.ChatController;
import com.example.demo.Services.AuthorizationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Aspect
public class MustAuthorizedAspect {

    private final AuthorizationService authorizationService;

    public MustAuthorizedAspect(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @Around("execution(* com.example.demo.Controllers.ChatController.*(..))")
    public Object mustAuthorized(ProceedingJoinPoint pjp) throws Throwable {
        if (authorizationService.isAuthorized()) {
            return pjp.proceed();
        } else {
            if (pjp.getSignature().getDeclaringType().equals(ChatController.class)) {
                return "redirect:/authorization";
            } else {
                // Для методов, возвращающих ResponseEntity
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authorized");
            }
        }
    }

}
