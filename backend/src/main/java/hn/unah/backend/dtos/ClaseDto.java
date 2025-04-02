package hn.unah.backend.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClaseDto {

    private int codigoClase;

    private String nombre;

    private int tarifa;

    private int capacidadMaxima;

    private List<AsientoDto> asiento;

}
