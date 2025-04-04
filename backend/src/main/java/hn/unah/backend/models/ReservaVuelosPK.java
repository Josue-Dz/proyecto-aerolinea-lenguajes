package hn.unah.backend.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReservaVuelosPK {
    
     @Column(name ="codigo_reserva")
    private int codigoReserva;

    @Column(name ="codigo_vuelo")
    private int codigoVuelo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservaVuelosPK)) return false;
        ReservaVuelosPK that = (ReservaVuelosPK) o;
        return codigoReserva == that.codigoReserva && codigoVuelo == that.codigoVuelo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoReserva, codigoVuelo);
    }


}
