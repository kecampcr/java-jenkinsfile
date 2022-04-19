package curso.cpic.cr.localizacion.dao;

import curso.cpic.cr.localizacion.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaDao extends JpaRepository<Provincia, Integer> {
}
