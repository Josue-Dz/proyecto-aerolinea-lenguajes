package hn.unah.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AsientoDto {

    private int codigoAsiento;

    private int tarifa;

    private VueloDto vuelo;

    private ClaseDto clase;

    private BoletoDto boleto;

    private AvionDto avion;

    private EstadoAsientoDto estadoAsiento;
}
