package hn.unah.backend.dtos;

import java.time.LocalDate;

public record BuscarReservasRequest(int origen, int destino, LocalDate fechaSalida,
                                    LocalDate fechaRegreso, int cantidadViajeros) {

}
