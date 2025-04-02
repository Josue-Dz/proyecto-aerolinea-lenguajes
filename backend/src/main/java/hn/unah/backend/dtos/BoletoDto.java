package hn.unah.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoletoDto {

    private int codigoBoleto;

    private int precioTotal;

    private int distanciaRecorrida;

    private VueloDto vuelo;

    private UsuarioDto usuario;

    private AsientoDto asiento;

}
