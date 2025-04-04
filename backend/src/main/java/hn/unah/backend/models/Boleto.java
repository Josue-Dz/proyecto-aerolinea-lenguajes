package hn.unah.backend.models;

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
    private int precioTotal;

    @Column(name = "distancia_recorrida")
    private int distanciaRecorrida;

    @ManyToOne
    @JoinColumn(name = "codigo_vuelo", referencedColumnName = "codigo_vuelo", nullable = false)
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_asiento", referencedColumnName = "codigo_asiento")
    private Asiento asiento;

    private boolean pagado = false;

}
