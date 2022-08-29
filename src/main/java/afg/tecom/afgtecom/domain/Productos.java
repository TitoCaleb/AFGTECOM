package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Table
@Entity
@Data
@ToString
public class Productos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id", precision = 10)
    private Integer id;

    @Digits(integer = 10, fraction = 0)
    @Column(name = "cantidad", precision = 10)
    private Integer cantidad;

    @NotNull
    @Column(name="precio", nullable = false, precision = 18, scale = 2)
    @Digits(integer = 16, fraction = 2)
    private BigDecimal precio;

    @Column(name = "detalle")
    @Size(max = 255)
    private String detalle;

    @Size(max = 140)
    @Column(name = "ubicacion", length = 140)
    private String ubicacion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedores proveedor;
}
