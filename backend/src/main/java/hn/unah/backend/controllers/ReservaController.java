package hn.unah.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.services.AsientoService;
import hn.unah.backend.services.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public class ReservaController {

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private VueloService vueloService;

     @Operation(summary = "Obtener datos para realizar una reserva", description = "Este endpoint obtiene los asientos disponibles y la información del vuelo relacionado con el código de reserva proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })

    @GetMapping("/reservar/{codigoReserva}")
    public ResponseEntity<Map<String, Object>> obtenerDatosParaReserva(@PathVariable(name = "codigoReserva") int codigoReserva) {
        Map<String, Object> response = new HashMap<>();
        response.put("asientos", asientoService.obtenerAsientoPorVuelo(codigoReserva));
        response.put("vuelo", vueloService.obtenerVueloPorId(codigoReserva));
        return ResponseEntity.ok(response);
    }

}
