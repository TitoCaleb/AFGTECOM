package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Table(name = "proveedores")
@Entity
@Data
@ToString
public class Proveedores implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Integer id;

    @Size(max = 255)
    @Column(name = "nombre_identidad", nullable = false, unique = true)
    private String nombreIdentidad;

    @Size(max = 20)
    @Column(name = "documento_identidad", nullable = false, unique = true)
    private String documentoIdentidad;

    @Size(max = 20)
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;

    @Size(max = 255)
    @Column(name = "pagina_web")
    private String paginaWeb;

    @ManyToOne
    @JoinColumn(name = "contacto_id")
    private Contactos contacto;
}
