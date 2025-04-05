package hn.unah.backend.models;

// import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
