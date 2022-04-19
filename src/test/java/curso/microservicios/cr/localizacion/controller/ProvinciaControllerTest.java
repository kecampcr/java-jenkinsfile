package curso.microservicios.cr.localizacion.controller;

import curso.microservicios.cr.localizacion.bo.ProvinciaBo;
import curso.microservicios.cr.localizacion.model.Provincia;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class ProvinciaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProvinciaBo provinciaBo;
    private List<Provincia> lista = Arrays.asList(new Provincia(1, "San José"),
            new Provincia(2, "Alajuela"));

    public ProvinciaControllerTest(){}

    @Test
    public void shouldBeReturnListOfProvincia() throws Exception {
        given(provinciaBo.findAll()).willReturn(lista);

        this.mockMvc.perform(get(ProvinciaController.URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void shouldFind_ProvinciaById() throws Exception {
        given(provinciaBo.findById(any())).willReturn(Optional.ofNullable(lista.get(0)));
        int expected = lista.get(0).getCodProvincia();
        this.mockMvc.perform(get(ProvinciaController.URI+"/{id}",lista.get(0).getCodProvincia())) //param("name", "Spring Community"))
                .andDo(print()).
                andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codProvincia").value(expected));

    }
}
