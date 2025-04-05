package hn.unah.backend.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TarjetaDto {

    private int codigoTarjeta;

    private Long numero;

    private String nombre;

    private int cvv;

    private LocalDate fechaEmision;

    private LocalDate fechaVencimiento;

    private int saldo;

    private UsuarioDto usuario;

}
