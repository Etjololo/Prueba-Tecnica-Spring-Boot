package com.pruebatecnica.apisuperheroes.modelos;

import lombok.Getter;

import java.util.Date;

@Getter
public class CustomErrorJson {
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Date timestamp;

    public CustomErrorJson(int status, String error, String message, String path, Date timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }
}
