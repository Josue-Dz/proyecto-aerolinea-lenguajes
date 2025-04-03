package hn.unah.backend.dtos;

import java.time.LocalDateTime;
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

    private LocalDateTime horaSalida;

    private LocalDateTime horaLlegada;

    private AeropuertoDto aeropuertoLlegada;

    private AeropuertoDto aeropuertoSalida;

    private List<BoletoDto> boletos;

    private EstadoDto estado;

}
