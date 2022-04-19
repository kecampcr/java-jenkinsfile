package curso.microservicios.cr.localizacion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/")
    private String sayHello(){
        return "Hello";
    }

}
