package hn.unah.backend.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoBoletoDto {
    
    private int codigoEstado;

    private String nombre;

    private List<BoletoDto> boletos;
}