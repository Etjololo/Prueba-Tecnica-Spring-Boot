package com.pruebatecnica.apisuperheroes.controlador;

import com.pruebatecnica.apisuperheroes.dto.SuperheroeResponse;
import com.pruebatecnica.apisuperheroes.servicios.SuperheroeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superheroes")
@RequiredArgsConstructor
public class SuperheroeController {
    private final SuperheroeService superheroeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SuperheroeResponse> getAllSuperheroes() {
        return superheroeService.getAllSuperheroes();
    }
}
