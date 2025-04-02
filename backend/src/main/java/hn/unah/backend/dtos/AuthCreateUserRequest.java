package hn.unah.backend.dtos;

import java.time.LocalDate;

public record AuthCreateUserRequest(String nombre, String apellido,
                            String correo, String contrasenia, LocalDate fechaNacimiento) {

}
