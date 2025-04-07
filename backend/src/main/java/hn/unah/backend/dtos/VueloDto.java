package hn.unah.backend.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VueloDto {

    private int codigoVuelo;

    private LocalDateTime fechaHoraLlegada;

    private LocalDateTime fechaHoraSalida;

    private LocalTime duracion;

    private AeropuertoDto aeropuertoSalida;

    private AeropuertoDto aeropuertoLlegada;

    private List<BoletoDto> boletos;

    private EstadoVueloDto estado;

    private List<AsientoDto> asientos;

    private AvionDto avion;

    private List<ReservaDto> reservas;
    
}
