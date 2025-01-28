package com.example.demo.AdviceControllers;

import com.example.demo.Exceptions.NullParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalNullParameterController {

    private final static Logger LOGGER = Logger.getLogger(GlobalNullParameterController.class.getName());

    @ExceptionHandler(NullParameterException.class)
    public String handleNullParameterException(NullParameterException ex) {
        LOGGER.warning(ex.getMessage());
        return ex.getMessage();
    }

}
