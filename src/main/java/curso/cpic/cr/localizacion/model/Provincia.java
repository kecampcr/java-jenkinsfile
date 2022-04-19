package curso.cpic.cr.localizacion.model;

import lombok.Data;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@XmlRootElement
@Table(name = "PROVINCIA")
public class Provincia {

    @Id
    @Basic(optional = false)
    @Column(name="COD_PROVINCIA")
    private Integer codProvincia;
    @Column(name="NOM_PROVINCIA")
    private String nomProvincia;

}
