package curso.microservicios.cr.localizacion.bo;

import curso.microservicios.cr.localizacion.dao.ProvinciaDao;
import curso.microservicios.cr.localizacion.model.Provincia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("ProvinciaBo")
@Transactional
@Slf4j

public class ProvinciaBoImpl implements ProvinciaBo{

    @Autowired
    ProvinciaDao dao;

    @Override
    public List<Provincia> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Provincia> findById(Integer id) {
        //return Optional.empty();
        Optional<Provincia> provincia = dao.findById(id);
        if(!provincia.isPresent()){
            throw new RuntimeException("No se encontró la provincia con id:" + id);
        }
        return provincia;
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public Provincia save(Provincia prov) {
        Optional<Provincia>provincia = dao.findById(prov.getCodProvincia());
        Provincia respuesta = null;
        if(provincia.isPresent()){
            throw new RuntimeException("Ya existe una provincia con id: " +  prov.getCodProvincia());
        }else{
            respuesta = dao.save(prov);
        }
        return respuesta;
    }

    @Override
    public Provincia update(Provincia prov) {
        Optional<Provincia>provincia = dao.findById(prov.getCodProvincia());
        Provincia respuesta = null;
        if(provincia.isPresent()){
            respuesta = dao.save(prov);
        }else{
            throw new RuntimeException("No se encontró la provincia: " + prov.getCodProvincia());
        }
        return respuesta;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Provincia>provincia = dao.findById(id);
        if(provincia.isPresent()){
            dao.deleteById(id);
        }else{
            throw new RuntimeException("No se encontró una provincia con id:" + id);
        }
    }
}
