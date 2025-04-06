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
@Table(name = "aviones")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_avion")
    private int codigoAvion;

    private String marca;

    @Column(name = "anio_fabricacion")
    private int anioFabricacion;

    @ManyToOne
    @JoinColumn(name = "codigo_tipo_avion", referencedColumnName = "codigo_tipo_avion", nullable = false)
    private TipoAvion tipoAvion;

    @OneToMany(mappedBy = "avion")
    private List<Vuelo> vuelos;

    @OneToMany(mappedBy = "avion")
    private List<Asiento> asientos;

}
