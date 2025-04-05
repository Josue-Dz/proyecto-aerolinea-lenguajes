package hn.unah.backend.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor 
@AllArgsConstructor
public class ReservaVuelosId {
    
     @Column(name ="codigo_reserva")
    private int codigoReserva;

    @Column(name ="codigo_vuelo")
    private int codigoVuelo;

}
