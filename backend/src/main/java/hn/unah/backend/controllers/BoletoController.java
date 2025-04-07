package hn.unah.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.services.BoletoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/boletos")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

     @Operation(
        summary = "Realiza el pago de un boleto",
        description = "Este endpoint permite realizar el pago de un boleto especificado por su código. Retorna el estado del pago."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago realizado con éxito"),
        @ApiResponse(responseCode = "400", description = "Pago no realizado, error de negocio"),
        @ApiResponse(responseCode = "404", description = "El boleto especificado no fue encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno en el servidor")
    })

    @PostMapping("/pagar/{codigoBoleto}")
    public ResponseEntity<String> pagarBoleto(@PathVariable(name = "codigoBoleto") int codigoBoleto) {
        try {
            String resultado = boletoService.pagarBoleto(codigoBoleto);
            if (resultado.contains("éxito")) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
            }
        } catch (EntityNotFoundException e) {
            // Manejo cuando el boleto no es encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Error general
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al procesar el pago.");
        }
    }

}
