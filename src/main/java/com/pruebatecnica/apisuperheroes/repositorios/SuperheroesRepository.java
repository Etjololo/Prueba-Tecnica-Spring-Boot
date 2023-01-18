package com.pruebatecnica.apisuperheroes.repositorios;

import com.pruebatecnica.apisuperheroes.modelos.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroesRepository extends JpaRepository<Superheroe, Long> {
}
