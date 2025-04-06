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
public class AeropuertoDto {
    
    private int codigoAeropuerto;

    private String nombre;

    private LugarDto lugar;

    private int capacidadAviones;

    private List<VueloDto> vuelosSalida;

    private List<VueloDto> vuelosLlegada;
}
