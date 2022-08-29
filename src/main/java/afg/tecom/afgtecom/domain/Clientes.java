package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@ToString
@Table(name = "clientes")
public class Clientes implements Serializable {

    @Id
    @Column(name="clientes_id", precision = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @Column(name="telefono", nullable = false,length = 20)
    private String telefono;

    @Size(max = 255)
    @Column(name = "nombre_identidad", nullable = false, unique = true)
    private String nombreIdentidad;

    @Size(max = 20)
    @Column(name="documento_identidad", unique = true, length = 20)
    private String documentoIdentidad;

    @Size(max=255)
    @Column(name = "direccion")
    private String direccion;

    @Size(max = 50)
    @Column(name = "ciudad", length = 50)
    private String ciudad;

    @Size(max = 50)
    @Column(name = "provincia")
    private String provincia;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Clientes.contacto ==> Contactos.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @JoinColumn(name = "contacto_id")
    @ManyToOne
    private Contactos contacto;
}
