package hn.unah.backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "aeropuertos")
public class Aeropuerto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_aeropuerto")
    private int codigAeropuerto;

    private String nombre;

    @Column(name = "capacidad_aviones")
    private int capacidadAviones;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_lugar", referencedColumnName = "codigo_lugar")
    private Lugar lugar;

    @OneToMany(mappedBy = "aeropuertoLlegada")
    private List<Vuelo> vuelosSalida;

    @OneToMany(mappedBy = "aeropuertoSalida")
    private List<Vuelo> vuelosLlegada;

}
