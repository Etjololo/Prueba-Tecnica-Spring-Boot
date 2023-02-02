package com.pruebatecnica.apisuperheroes.controlador;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser("spring")
public class SuperheroeControllerIntegrationTests {
    private static final String BASE_URL = "/api/superheroes";

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getAllSuperheroes_OK200() throws Exception {
        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].nombre").value("Spiderman"));
    }

    @Test
    public void getSuperheroe_OK200() throws Exception {
        mvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("nombre").value("Spiderman"));
    }

    @Test
    public void getSuperheroeByName_OK200() throws Exception {
        mvc.perform(get(BASE_URL + "/name/wolv"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].nombre").value("Wolverine"));
    }

    @Test
    public void deleteSuperheroe_OK200() throws Exception {
        mvc.perform(delete(BASE_URL + "/3"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("nombre").value("Superman"));
    }

    @Test
    public void getSuperheroe_404() throws Exception {
        mvc.perform(get(BASE_URL + "/6"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateSuperheroe_OK200() throws Exception {
        mvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"id\": 2,\n" +
                                "    \"nombre\": \"Daredevil\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("nombre").value("Daredevil"));
    }

    @Test
    public void deleteSuperheroe_404() throws Exception {
        mvc.perform(delete(BASE_URL + "/6"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateSuperheroe_404() throws Exception {
        mvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"id\": 6,\n" +
                                "    \"nombre\": \"Daredevil\"\n" +
                                "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void handleExceptionTest() throws Exception {
        mvc.perform(get(BASE_URL + "/6++"))
                .andExpect(status().is5xxServerError());
    }
}
