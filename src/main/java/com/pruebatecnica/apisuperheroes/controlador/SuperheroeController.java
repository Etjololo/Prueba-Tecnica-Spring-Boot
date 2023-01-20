package com.pruebatecnica.apisuperheroes.controlador;

import com.pruebatecnica.apisuperheroes.dto.SuperheroeResponse;
import com.pruebatecnica.apisuperheroes.servicios.SuperheroeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/superheroes")
@RequiredArgsConstructor
public class SuperheroeController {

    private final SuperheroeService superheroeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CachePut("superheroes")
    public List<SuperheroeResponse> getAllSuperheroes() {
        return superheroeService.getAllSuperheroes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CachePut("superheroe")
    public SuperheroeResponse getSuperheroe(@PathVariable Long id) {
        return superheroeService.getSuperheroe(id);
    }

    @GetMapping("/name/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    @CachePut("superheroeByName")
    public List<SuperheroeResponse> getSuperheroeByName(@PathVariable String nombre) {
        return superheroeService.getSuperheroeByName(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuperheroeResponse> deleteSuperheroe(@PathVariable Long id) {
        SuperheroeResponse isRemoved = superheroeService.deleteSuperheroe(id);

        if (isRemoved == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(isRemoved, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperheroeResponse> updateSuperheroe(@PathVariable Long id, @RequestBody SuperheroeResponse superheroeResponse) {
        SuperheroeResponse isRemoved = superheroeService.updateSuperheroe(id, superheroeResponse);

        if (isRemoved == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(isRemoved, HttpStatus.OK);
    }
}
