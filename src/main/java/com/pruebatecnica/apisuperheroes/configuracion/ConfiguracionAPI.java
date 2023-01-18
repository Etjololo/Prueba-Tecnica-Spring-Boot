package com.pruebatecnica.apisuperheroes.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionAPI {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
