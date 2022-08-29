package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@ToString
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion implements Serializable {

    @Id
    @Column(name = "cotizacion_detalle_id", precision = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "nro_item", nullable = false, precision = 10)
    private Integer nroItem;

    @Digits(integer = 16, fraction = 2)
    @NotNull
    @Column(name = "precio_unitario", nullable = false, precision = 18, scale = 2)
    private BigDecimal precioUnitario;

    @Digits(integer = 16, fraction = 2)
    @NotNull
    @Column(name = "subtotal", nullable = false, precision = 18, scale = 2)
    private BigDecimal subtotal;

    @Digits(integer = 16, fraction = 2)
    @NotNull
    @Column(name = "total", nullable = false, precision = 18, scale = 2)
    private BigDecimal total;

    @NotNull
    @JoinColumn(name = "cotizacion_id", nullable = false)
    @ManyToOne
    private Cotizaciones cotizacion;

    @NotNull
    @JoinColumn(name = "producto_id", nullable = false)
    @ManyToOne
    private Productos producto;
}
