package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@ToString
@Table(name = "categoria")
public class Categoria implements Serializable {

    @Id
    @Column(name="categoria_id", precision = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 140)
    @Column(name="nombre")
    private String nombre;
}
