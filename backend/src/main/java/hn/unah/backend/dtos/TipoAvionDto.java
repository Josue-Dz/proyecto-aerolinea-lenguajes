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
public class TipoAvionDto {

    private int codigoTipoAvion;

    private String tipoAvion;

    private String modelo;

    private List<AvionDto> aviones;

}
