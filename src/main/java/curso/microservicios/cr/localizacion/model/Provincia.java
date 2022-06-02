package curso.microservicios.cr.localizacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@XmlRootElement
@Table(name = "PROVINCIA")
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

    @Id
    @Basic(optional = false)
    @Column(name="COD_PROVINCIA")
    private Integer codProvincia;
    @Column(name="NOM_PROVINCIA")
    private String nomProvincia;


}
