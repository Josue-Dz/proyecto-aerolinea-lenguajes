package hn.unah.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.models.Vuelo;
import hn.unah.backend.services.VueloService;

@RestController
@RequestMapping("/api/vuelo")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @GetMapping("/reservados/futuros/{correoUsuario}")
    public ResponseEntity<Object> obtenerVuelosReservadosFuturos(@PathVariable String correoUsuario) {
        try {
            List<Vuelo> vuelosFuturos = vueloService.obtenerVuelosReservadosFuturos(correoUsuario);

            if (vuelosFuturos.isEmpty()) {
                // Si no se encuentran vuelos, retornamos un mensaje con código 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron vuelos reservados en el futuro para el usuario: " + correoUsuario);
            }

            // Si se encuentran vuelos, retornamos los vuelos con código 200
            return ResponseEntity.ok(vuelosFuturos);
        } catch (Exception e) {
            // En caso de un error inesperado, retornamos un código 500 con un mensaje
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/historial/{correoUsuario}")
    public ResponseEntity<?> obtenerHistorialDeVuelos(@PathVariable String correoUsuario) {
        try {
            // Validación de correo
            if (correoUsuario == null || !correoUsuario.contains("@")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Correo electrónico inválido.");
            }

            Map<String, List<Vuelo>> historial = vueloService.obtenerHistorialDeVuelos(correoUsuario);

            boolean historialVacio = historial.get("vuelosPasados").isEmpty() &&
                    historial.get("vuelosFuturos").isEmpty();

            if (historialVacio) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron vuelos para el usuario con correo: " + correoUsuario);
            }

            return ResponseEntity.ok(historial);

        } catch (Exception e) {
            // En caso de un error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error al obtener el historial de vuelos: " + e.getMessage());
        }
    }
}
