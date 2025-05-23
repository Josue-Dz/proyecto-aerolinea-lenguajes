package hn.unah.backend.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_vuelo")
    private int codigoVuelo;

    @Column(name = "fecha_hora_salida")
    private LocalDateTime fechaHoraSalida;

    @Column(name = "fecha_hora_llegada")
    private LocalDateTime fechaHoraLlegada;

    @Column(name = "duracion")
    private LocalTime duracion;

    @ManyToOne
    @JoinColumn(name = "codigo_aeropuerto_salida", referencedColumnName = "codigo_aeropuerto", nullable = false)
    private Aeropuerto aeropuertoSalida;

    @ManyToOne
    @JoinColumn(name = "codigo_aeropuerto_llegada", referencedColumnName = "codigo_aeropuerto", nullable = false)
    private Aeropuerto aeropuertoLlegada;


    @ManyToOne
    @JoinColumn(name = "codigo_estado", referencedColumnName = "codigo_estado", nullable = false)
    private EstadoVuelo estadoVuelo;

    @OneToMany(mappedBy = "vuelo")
    private List<Asiento> asientos;

    @ManyToOne
    @JoinColumn(name = "codigo_avion", referencedColumnName = "codigo_avion", nullable = false)
    private Avion avion;

    @ManyToMany(mappedBy = "vuelos")
    private List<Reserva> reserva;
}
