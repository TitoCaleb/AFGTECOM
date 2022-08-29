package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    @Id
    @Column(name = "rol_id", precision = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 140)
    @Column(name = "nombre", length = 140)
    private String nombre;
}
