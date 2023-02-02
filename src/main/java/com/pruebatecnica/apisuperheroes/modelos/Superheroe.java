package com.pruebatecnica.apisuperheroes.modelos;

import lombok.*;

import javax.persistence.*;

@Entity(name = "H2_Superheroes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "H2_Superheroes")
public class Superheroe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}
