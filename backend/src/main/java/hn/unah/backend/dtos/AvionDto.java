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
public class AvionDto {

    private int codigoAvion;

    private String marca;

    private int anioFabricacion;

    private TipoAvionDto tipoAvion;

    private List<VueloDto> vuelo;

    private List<AsientoDto> asientos;

}
