package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@ToString
@Table(name = "contactos")
public class Contactos implements Serializable {


    @Id
    @Column(name = "contacto_id", precision = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 140)
    @Column(name = "nombre", nullable = false, length = 140)
    private String nombre;

    @Size(max = 20)
    @Column(name = "celular", nullable = false, length = 20)
    private String celular;

    @Size(max = 140)
    @Column(name = "correo", nullable = false, length = 140)
    private String correo;

    @Size(max = 20)
    @Column(name = "cargo", nullable = false, length = 20)
    private String cargo;
}
