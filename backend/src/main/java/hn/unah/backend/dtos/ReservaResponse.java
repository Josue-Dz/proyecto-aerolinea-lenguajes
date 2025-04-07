package hn.unah.backend.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"origen", "destino", "estadoVuelo", "horaSalida", "horaLlegada", "precio", "codigoReserva"})
public record ReservaResponse(String origen, String destino, String estadoVuelo,
                                LocalDateTime horaSalida, LocalDateTime horaLlegada, Double precio, int codigoReserva) {

}