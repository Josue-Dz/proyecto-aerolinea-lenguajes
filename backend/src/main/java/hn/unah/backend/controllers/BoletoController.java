package hn.unah.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.services.BoletoService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/boletos")
public class BoletoController {

    private BoletoService boletoService;

    @PostMapping("/{codigoBoleto}/pagar")
    public ResponseEntity<String> pagarBoleto(@PathVariable int codigoBoleto) {
        try {
            String resultado = boletoService.pagarBoleto(codigoBoleto);
            if (resultado.contains("éxito")) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al procesar el pago.");
        }
    }

}
