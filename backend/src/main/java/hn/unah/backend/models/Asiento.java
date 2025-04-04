package hn.unah.backend.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "asientos")
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_asiento")
    private int codigoAsiento;

    private int tarifa;

    @ManyToOne
    @JoinColumn(name = "codigo_vuelo", referencedColumnName = "codigo_vuelo")
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "codigo_clase", referencedColumnName = "codigo_clase", nullable = false)
    private Clase clase;

    @OneToMany(mappedBy = "asiento")
    private List<Boleto> boleto;

    @ManyToOne
    @JoinColumn(name = "codigo_avion", referencedColumnName = "codigo_avion")
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "codigo_estado", referencedColumnName = "codigo_estado")
    private EstadoAsiento estadoAsiento;

}
