package afg.tecom.afgtecom.domain;

import com.sun.jdi.NativeMethodException;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@ToString
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(name = "new_user", columnNames = {"nombre", "celular", "correo"})})
public class Usuarios implements Serializable {

    @Id
    @Column(name = "usuario_id", precision = 10)
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

    @ManyToOne
    @NotNull
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}
