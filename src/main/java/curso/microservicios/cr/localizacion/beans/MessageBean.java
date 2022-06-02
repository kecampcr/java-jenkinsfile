package curso.microservicios.cr.localizacion.beans;

import lombok.Data;
import java.util.Date;
@Data
public class MessageBean {


        private String applicationName="";
        private String description="";
        private String version="";
        private String stage="";
        private Date fecha;
        private String hostname="";


}
