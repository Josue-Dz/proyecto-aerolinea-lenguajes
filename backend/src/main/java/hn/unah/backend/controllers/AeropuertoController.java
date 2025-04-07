package hn.unah.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import hn.unah.backend.dtos.AeropuertoDto;
import hn.unah.backend.services.AeropuertoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/aeropuerto")
@CrossOrigin(origins ="http://127.0.0.1:8000")
public class AeropuertoController {
    
    @Autowired
    private AeropuertoService aeropuertoService;

    @Operation(
        summary = "Obtener aeropuertos por código de vuelo",
        description = "Devuelve la lista de aeropuertos (origen y destino) asociados a un vuelo específico según su código."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aeropuertos encontrados exitosamente"),
        @ApiResponse(responseCode = "204", description = "No se encontraron aeropuertos para el vuelo especificado"),
        @ApiResponse(responseCode = "400", description = "Petición incorrecta o código de vuelo inválido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/aeropuertos")
    public ResponseEntity<?> obtenerTodosLosAeropuertos() {
        try {
            List<AeropuertoDto> aeropuertos = aeropuertoService.obtenerTodosLosAeropuertos();
    
            if (aeropuertos == null || aeropuertos.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204
            }
    
            return ResponseEntity.ok(aeropuertos); // 200 OK
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al obtener los aeropuertos: " + e.getMessage());
        }
    }

}

