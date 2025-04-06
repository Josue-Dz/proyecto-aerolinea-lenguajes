package hn.unah.backend.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "boletos")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_boleto")
    private int codigoBoleto;

    @Column(name = "precio_total")
    private BigDecimal precioTotal;

    @Column(name = "distancia_recorrida")
    private Double distanciaRecorrida;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_asiento", referencedColumnName = "codigo_asiento")
    private Asiento asiento;

    @ManyToOne
    @JoinColumn(name = "codigo_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "codigo_estado", referencedColumnName = "codigo_estado")
    private EstadoBoleto estado;

}
