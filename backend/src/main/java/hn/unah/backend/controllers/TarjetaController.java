package hn.unah.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.dtos.TarjetaDto;
import hn.unah.backend.models.Tarjeta;
import hn.unah.backend.services.TarjetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/tarjeta")
@CrossOrigin(origins ="http://127.0.0.1:8000")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;


    
    @Operation(summary = "Guardar una nueva tarjeta", description = "Este endpoint permite guardar una nueva tarjeta en el sistema."
    +
    " Si la tarjeta ya existe, retorna un conflicto (409).")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Tarjeta creada correctamente"),
    @ApiResponse(responseCode = "409", description = "La tarjeta ya existe"),
    @ApiResponse(responseCode = "500", description = "Error interno al guardar la tarjeta")
})

    @PostMapping("/guardar")
    public ResponseEntity<Map<String, Object>> guardarTarjeta(@RequestBody TarjetaDto tarjetaDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Llamamos al servicio para guardar la tarjeta
            Tarjeta tarjetaGuardada = tarjetaService.guardarTarjeta(tarjetaDto);

            // Si la tarjeta es null, significa que ya existe, así que retornamos un
            // conflicto
            if (tarjetaGuardada == null) {
                response.put("mensaje", "La tarjeta ya existe.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            // Si la tarjeta se guarda correctamente, retornamos un 201 (creado)
            response.put("mensaje", "Tarjeta creada correctamente.");
            response.put("tarjeta", tarjetaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Si ocurre un error, retornamos un error 500
            response.put("mensaje", "Hubo un error al guardar la tarjeta.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Obtener tarjetas de un cliente", description = "Este endpoint permite obtener todas las tarjetas asociadas "+
    "al correo del usuario especificado.")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Tarjetas encontradas", 
             content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
             @ApiResponse(responseCode = "404", description = "No se encontraron tarjetas para el usuario"),
             @ApiResponse(responseCode = "500", description = "Error interno al obtener las tarjetas")
     })

    @GetMapping("/obtener/{correoUsuario}")
    public ResponseEntity<Map<String, Object>> obtenerTarjetasPorCliente(@PathVariable String correoUsuario) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Llamamos al servicio para obtener las tarjetas del usuario
            List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasPorUsuario(correoUsuario);

            if (!tarjetas.isEmpty()) {
                // Si se encuentran tarjetas, se retorna un código 200 OK
                response.put("mensaje", "Tarjetas encontradas.");
                response.put("tarjetas", tarjetas);
                return ResponseEntity.ok(response);
            } else {
                // Si no se encuentran tarjetas, se retorna un código 404 Not Found
                response.put("mensaje", "No se encontraron tarjetas para el usuario.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            // Si ocurre una excepción, retornamos un error 500 con el mensaje
            response.put("mensaje", "Hubo un error al obtener las tarjetas.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
