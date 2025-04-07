package hn.unah.backend.dtos;

import java.math.BigDecimal;

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

    private BigDecimal latitud;

    private BigDecimal longitud;

    private AeropuertoDto aeropuertos;

    private PaisDto pais;
}
