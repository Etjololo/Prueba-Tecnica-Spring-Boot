package com.pruebatecnica.apisuperheroes.servicios;

import com.pruebatecnica.apisuperheroes.dto.SuperheroeResponse;
import com.pruebatecnica.apisuperheroes.modelos.Superheroe;
import com.pruebatecnica.apisuperheroes.repositorios.SuperheroesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperheroeService {
    private final SuperheroesRepository superheroesRepository;

    private ModelMapper modelMapper;

    public List<SuperheroeResponse> getAllSuperheroes() {
        List<SuperheroeResponse> superheroeResponse = new ArrayList<>();
        List<Superheroe> superheroes = superheroesRepository.findAll();

        for (Superheroe superheroe : superheroes) {
            superheroeResponse.add(mapToSuperheroeResponse(superheroe));
        }

        return superheroeResponse;
    }

    private SuperheroeResponse mapToSuperheroeResponse(Superheroe superheroe) {
        SuperheroeResponse superheroeResponse = modelMapper.map(superheroe, SuperheroeResponse.class);
        superheroeResponse.setId(superheroe.getId());
        superheroeResponse.setNombre(superheroe.getNombre());

        return superheroeResponse;
    }
}
