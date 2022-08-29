package afg.tecom.afgtecom.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "cotizaciones")
public class Cotizaciones implements Serializable {

    @Id
    @Column(name = "cotizacion_id", precision = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "fechaEmision", nullable = false, length = 29)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;

    @NotNull
    @JoinColumn(name = "cliente_id", nullable = false)
    @ManyToOne
    private Clientes cliente;

    @NotNull
    @JoinColumn(name = "usuario_id", nullable = false)
    @ManyToOne
    private Usuarios usuario;

}
