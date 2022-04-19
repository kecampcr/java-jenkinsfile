package curso.microservicios.cr.localizacion.dao;

import curso.microservicios.cr.localizacion.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaDao extends JpaRepository<Provincia, Integer> {
}
