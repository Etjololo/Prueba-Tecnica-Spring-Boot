package com.pruebatecnica.apisuperheroes.servicios;

import com.pruebatecnica.apisuperheroes.dto.SuperheroeResponse;
import com.pruebatecnica.apisuperheroes.modelos.Superheroe;
import com.pruebatecnica.apisuperheroes.repositorios.SuperheroesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SuperheroeServiceTests {
    @InjectMocks
    private SuperheroeService superheroeService;

    @Mock
    private SuperheroesRepository superheroesRepository;

    @Test
    public void getAllSuperheroesTest() throws Exception {
        when(superheroesRepository.findAll()).thenReturn(List.of(new Superheroe(), new Superheroe()));

        assertThat(superheroeService.getAllSuperheroes()).hasSize(2);
        verify(superheroesRepository, times(1)).findAll();
        verifyNoMoreInteractions(superheroesRepository);
    }

    @Test
    public void getSuperheroeTest() throws Exception {
        final var expectedSuperheroe = Superheroe.builder().nombre("Flash").build();
        when(superheroesRepository.existsById(anyLong())).thenReturn(true);
        when(superheroesRepository.findById(anyLong())).thenReturn(Optional.of(expectedSuperheroe));

        final var actualSuperheroe = superheroeService.getSuperheroe(anyLong());

        assertThat(actualSuperheroe).isEqualTo(mapToSuperheroeResponse(expectedSuperheroe));
        verify(superheroesRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getSuperheroeByNameTest() throws Exception {
        when(superheroesRepository.findAllByNombreContainsIgnoreCase(anyString())).thenReturn(List.of(new Superheroe()));

        assertThat(superheroeService.getSuperheroeByName(anyString())).hasSize(1);
        verify(superheroesRepository, times(1)).findAllByNombreContainsIgnoreCase(anyString());
        verifyNoMoreInteractions(superheroesRepository);
    }

    @Test
    public void deleteSuperheroeTest() throws Exception {
        final var expectedSuperheroe = Superheroe.builder().nombre("Flash").build();
        when(superheroesRepository.existsById(anyLong())).thenReturn(true);
        when(superheroesRepository.findById(anyLong())).thenReturn(Optional.of(expectedSuperheroe));
        doNothing().when(superheroesRepository).deleteById(anyLong());

        final var actualSuperheroe = superheroeService.deleteSuperheroe(anyLong());

        assertThat(actualSuperheroe).isEqualTo(mapToSuperheroeResponse(expectedSuperheroe));
        verify(superheroesRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void updateSuperheroeTest() throws Exception {
        final var expectedSuperheroe = Superheroe.builder().nombre("Flash").build();
        when(superheroesRepository.existsById(expectedSuperheroe.getId())).thenReturn(true);
        when(superheroesRepository.save(any(Superheroe.class))).thenReturn(expectedSuperheroe);

        final var actualSuperheroe = superheroeService.updateSuperheroe(new SuperheroeResponse());

        assertThat(actualSuperheroe).isEqualTo(mapToSuperheroeResponse(expectedSuperheroe));
        verify(superheroesRepository, times(1)).save(any(Superheroe.class));
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
