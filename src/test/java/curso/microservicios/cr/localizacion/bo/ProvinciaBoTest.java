package curso.microservicios.cr.localizacion.bo;

import curso.microservicios.cr.localizacion.dao.ProvinciaDao;
import curso.microservicios.cr.localizacion.model.Provincia;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
public class ProvinciaBoTest {



/**
 * Pruebas usando Mock al Dao
 * Se acelera el tiempo de ejecución de la prueba
 */


    @Mock
    private ProvinciaDao dao;


    @InjectMocks
    private ProvinciaBoImpl bo;

    Provincia provincia = new Provincia(1, "Provincia Prueba");

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void shouldReturnListOfAverias() {
        when(dao.findAll()).thenReturn(Collections.singletonList(provincia));
        log.info("Consulta: " + bo.findAll().toString());
        assertThat(bo.findAll().size() > 0).isTrue();
    }

    @Test
    void shouldBeReturnAEntity() {
        given(dao.findById(1)).willReturn(Optional.ofNullable(provincia));
        assertThat(bo.findById(1).isPresent()).isTrue();
        verify(dao, times(1)).findById(1);
    }

    @Test
    void shouldBeReturnANumberOfRows() {
        given(dao.count()).willReturn(1L);
        assertTrue(bo.count().equals(1L));
        verify(dao, times(1)).count();
    }


    @Test
    void shouldBeUpdateAValidAveria() {
        given(dao.findById(any())).willReturn(Optional.ofNullable(provincia));
        given(dao.save(any())).willReturn(provincia);
        Optional<Provincia> provincia = bo.findById(1);
        provincia.get().setNomProvincia("Provincia Actualizada");
        assertThat(bo.update(provincia.get())).isInstanceOf(Provincia.class);

    }

    @Test
    void shouldBedeleteAById() {
        Provincia provincia = this.provincia;
        given(dao.findById(any())).willReturn(Optional.ofNullable(provincia));
        doNothing().when(dao).deleteById(any());
        bo.deleteById(provincia.getCodProvincia());
        verify(dao, times(1)).deleteById(provincia.getCodProvincia());
    }
}