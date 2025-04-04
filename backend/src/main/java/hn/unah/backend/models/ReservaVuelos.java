package hn.unah.backend.models;

import java.io.Serializable;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="reservas_x_vuelos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservaVuelos implements Serializable{

    @EmbeddedId
    private ReservaVuelosPK id;

    @ManyToOne
    @JoinColumn(name ="codigo_reserva", insertable = false, updatable = false)
    @MapsId("codigoReserva")
    private Reserva reservas;

    @ManyToOne
    @JoinColumn(name ="codigo_vuelo", insertable = false, updatable = false)
    @MapsId("codigoVuelo")
    private Vuelo vuelo;
    
}
