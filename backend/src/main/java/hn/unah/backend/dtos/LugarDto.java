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
public class LugarDto {

    private int codigoLugar;

    private String nombre;

    private double latitud;

    private double longitud;

    private List<AeropuertoDto> aeropuertos;
}
