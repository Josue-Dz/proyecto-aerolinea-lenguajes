package hn.unah.backend.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto {

    private int codigoUsuario;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private String correo;

    private String contrasenia;

    private List<BoletoDto> boletos;

    private List<TarjetaDto> tarjetas;

}
