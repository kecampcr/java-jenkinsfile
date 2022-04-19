package curso.microservicios.cr.localizacion.controller;

import curso.microservicios.cr.localizacion.bo.ProvinciaBo;
import curso.microservicios.cr.localizacion.model.Provincia;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Getter
@Setter
@Slf4j
public class ProvinciaController {

    public static String URI="/Provincia";

    @Autowired
    private ProvinciaBo provinciaBo;

    @GetMapping(value = "/Provincia")
    public List<Provincia> listarProvincias(){
        log.info("listarProvincias...");
        return provinciaBo.findAll();
    }

    @GetMapping(value = "/Provincia/{id}")
    public ResponseEntity<Provincia> getOne(@PathVariable("id") Integer id){
        log.info(" getOne: " + id);
        ResponseEntity<Provincia> respuesta = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Optional<Provincia> result = provinciaBo.findById(id);
        if (result.isPresent()) {
            respuesta = new ResponseEntity<Provincia>(result.get(), HttpStatus.OK);
        } else {
            log.error("No se encontró la Provincia: " + id);
            headers.set("X-ErrorCode", "-1");
            headers.set("X-ErrorCause", "No se encontró información del código:" + id);
            respuesta = new ResponseEntity<Provincia>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

    @PostMapping(value = {"/Provincia", "/Provincia/"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provincia> save(@RequestBody Provincia entity) {
        log.info("save() -->" + entity.toString());
        Provincia newReg = null;
        try {
            newReg = provinciaBo.save(entity);
        } catch (Exception e) {
            log.error("ProvinciaController se ha presentado una excepción en save(): "+e.getMessage());
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-ErrorCode", "-1");
            headers.set("X-ErrorCause", e.getLocalizedMessage());
            return new ResponseEntity<Provincia>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(newReg);
    }

    @PutMapping(value = {"/Provincia"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provincia> update( @RequestBody Provincia entity) {
        log.info("update: " + entity.toString());
        ResponseEntity<Provincia> respuesta;
        try {
            Provincia updated = provinciaBo.update(entity);
            respuesta = new ResponseEntity<Provincia>(updated, null, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Se ha presentado una excepción en update(): "+e.getLocalizedMessage());
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-ErrorCode", "-1");
                        headers.set("X-ErrorCause", e.getLocalizedMessage());
            respuesta = new ResponseEntity<Provincia>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }


    @DeleteMapping(value = {"/Provincia/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("delete:-->" + id);
        ResponseEntity<Void> respuesta;
        try {
            provinciaBo.deleteById(id);
            respuesta = new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Se ha presentado una excepción en delete(): "+e.getMessage());
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-ErrorCode", "-1");
            headers.set("X-ErrorCause", e.getLocalizedMessage());
            respuesta = new ResponseEntity<Void>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

    @GetMapping(value = "Provincia/count")
    public ResponseEntity<Long> count(){
        log.info("count...");
        ResponseEntity<Long> respuesta = null;
        try {
            Long num = provinciaBo.count();
            respuesta = new ResponseEntity<Long>(num, HttpStatus.OK);

        } catch (Exception e) {
            log.error("EjecutivosController se ha presentado una excepción en getCount(): "+e.getMessage());
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-ErrorCode", "-1");
            headers.set("X-ErrorCause", e.getMessage());
            respuesta = new ResponseEntity<Long>(null, headers, HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

}
