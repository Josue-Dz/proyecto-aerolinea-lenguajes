package hn.unah.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.models.Boleto;
import hn.unah.backend.services.BoletoService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/boletos")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

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
