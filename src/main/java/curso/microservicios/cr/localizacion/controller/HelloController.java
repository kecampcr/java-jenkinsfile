package curso.microservicios.cr.localizacion.controller;

import curso.microservicios.cr.localizacion.beans.MessageBean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Getter
@Setter
public class HelloController {


    @Value("${info.app.name}")
    private String appName;
    @Value("${info.app.version}")
    private String version;
    @Value("${info.app.description}")
    private String description;

    @Autowired
    Environment env;

    @GetMapping(value = "/")
    private MessageBean sayHello(){
        String stage = "ND";
        String hostname="ND";
        if(null!=env.getProperty("STAGE")){
            stage = env.getProperty("STAGE");
        }
        if(null!=env.getProperty("HOSTNAME")){
            hostname= env.getProperty("HOSTNAME");
        }

        MessageBean messageBean = new MessageBean();
        messageBean.setFecha(new Date());
        messageBean.setVersion(version);
        messageBean.setApplicationName(appName);
        messageBean.setDescription(description);
        messageBean.setStage(stage);
        messageBean.setHostname(hostname);
        return messageBean;
    }

}
