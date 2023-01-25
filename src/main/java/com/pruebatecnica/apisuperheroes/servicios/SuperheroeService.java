package com.pruebatecnica.apisuperheroes.servicios;

import com.pruebatecnica.apisuperheroes.dto.SuperheroeResponse;
import com.pruebatecnica.apisuperheroes.modelos.Superheroe;
import com.pruebatecnica.apisuperheroes.repositorios.SuperheroesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SuperheroeService {
    private final SuperheroesRepository superheroesRepository;

    public List<SuperheroeResponse> getAllSuperheroes() {
        List<SuperheroeResponse> superheroeResponse = new ArrayList<>();
        List<Superheroe> superheroes = superheroesRepository.findAll();

        for (Superheroe superheroe : superheroes) {
            superheroeResponse.add(mapToSuperheroeResponse(superheroe));
        }

        return superheroeResponse;
    }

    public SuperheroeResponse getSuperheroe(Long id) {
        SuperheroeResponse superheroeResponse = new SuperheroeResponse();

        if (superheroesRepository.existsById(id)) {
            superheroeResponse = mapToSuperheroeResponse(superheroesRepository.findById(id).get());
        }

        return superheroeResponse;
    }

    public List<SuperheroeResponse> getSuperheroeByName(String nombre) {
        List<SuperheroeResponse> superheroeResponse = new ArrayList<>();
        List<Superheroe> superheroes = superheroesRepository.findAllByNombreContainsIgnoreCase(nombre);

        for (Superheroe superheroe : superheroes) {
            superheroeResponse.add(mapToSuperheroeResponse(superheroe));
        }

        return superheroeResponse;
    }

    public SuperheroeResponse deleteSuperheroe(Long id) {
        SuperheroeResponse superheroeResponse = new SuperheroeResponse();

        if (superheroesRepository.existsById(id)) {
            superheroeResponse = mapToSuperheroeResponse(superheroesRepository.findById(id).get());
            superheroesRepository.deleteById(id);
        }

        return superheroeResponse;
    }

    public SuperheroeResponse updateSuperheroe(SuperheroeResponse superheroeResponse) {
        Superheroe superheroe = mapToSuperheroe(superheroeResponse);

        if (superheroesRepository.existsById(superheroe.getId())) {
            superheroesRepository.save(superheroe);
        }

        return superheroeResponse;
    }

    private SuperheroeResponse mapToSuperheroeResponse(Superheroe superheroe) {
        SuperheroeResponse superheroeResponse = new SuperheroeResponse();
        superheroeResponse.setId(superheroe.getId());
        superheroeResponse.setNombre(superheroe.getNombre());

        return superheroeResponse;
    }

    private Superheroe mapToSuperheroe(SuperheroeResponse superheroeResponse) {
        Superheroe superheroe = new Superheroe();
        superheroe.setId(superheroeResponse.getId());
        superheroe.setNombre(superheroeResponse.getNombre());

        return superheroe;
    }
}
