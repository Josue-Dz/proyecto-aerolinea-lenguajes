package hn.unah.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.dtos.VueloDto;
import hn.unah.backend.services.VueloService;

@RestController
@RequestMapping("/api/vuelo")
@CrossOrigin(origins ="http://127.0.0.1:8000")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @GetMapping("/reservados/futuros/{idUsuario}")
    public ResponseEntity<Object> obtenerVuelosReservadosFuturos(@PathVariable int idUsuario) {
        try {
            List<VueloDto> vuelosFuturos = vueloService.obtenerVuelosReservadosFuturos(idUsuario);

            if (vuelosFuturos.isEmpty()) {
                // Si no se encuentran vuelos, retornamos un mensaje con código 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron vuelos reservados en el futuro para el usuario: ");
            }

            // Si se encuentran vuelos, retornamos los vuelos con código 200
            return ResponseEntity.ok(vuelosFuturos);
        } catch (Exception e) {
            // En caso de un error inesperado, retornamos un código 500 con un mensaje
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<?> obtenerHistorialDeVuelos(@PathVariable int idUsuario) {
        try {

            Map<String, List<VueloDto>> historial = vueloService.obtenerHistorialDeVuelos(idUsuario);

            boolean historialVacio = historial.get("vuelosPasados").isEmpty() &&
                    historial.get("vuelosFuturos").isEmpty();

            if (historialVacio) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron vuelos para el usuario");
            }

            return ResponseEntity.ok(historial);

        } catch (Exception e) {
            // En caso de un error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error al obtener el historial de vuelos: " + e.getMessage());
        }
    }
}
