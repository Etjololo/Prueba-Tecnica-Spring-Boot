package com.pruebatecnica.apisuperheroes.controlador;

import com.pruebatecnica.apisuperheroes.modelos.CustomErrorJson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class ExceptionManagementController {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomErrorJson handleException(Exception ex, HttpServletRequest request) {
        return new CustomErrorJson(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getName(),
                ex.getMessage(),
                request.getRequestURI(),
                new Date());
    }
}
