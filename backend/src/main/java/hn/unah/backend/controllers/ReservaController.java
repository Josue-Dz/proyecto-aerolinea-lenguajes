package hn.unah.backend.controllers;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.dtos.BuscarReservasRequest;
import hn.unah.backend.dtos.ReservaResponse;
import hn.unah.backend.services.AsientoService;
import hn.unah.backend.services.ReservaService;
import hn.unah.backend.services.VueloService;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public class ReservaController {

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private VueloService vueloService;

    @GetMapping("/reservar/{codigoReserva}")
    public ResponseEntity<Map<String, Object>> obtenerDatosParaReserva(@PathVariable(name = "codigoReserva") int codigoReserva) {
        Map<String, Object> response = new HashMap<>();
        response.put("asientos", asientoService.obtenerAsientoPorVuelo(codigoReserva));
        response.put("vuelo", vueloService.obtenerVueloPorId(codigoReserva));
        return ResponseEntity.ok(response);
    }

}
