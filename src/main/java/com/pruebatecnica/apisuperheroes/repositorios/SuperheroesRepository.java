package com.pruebatecnica.apisuperheroes.repositorios;

import com.pruebatecnica.apisuperheroes.modelos.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuperheroesRepository extends JpaRepository<Superheroe, Long> {
    Optional<Superheroe> findById(Long id);

    List<Superheroe> findAllByNombreContainsIgnoreCase(String nombre);
}
