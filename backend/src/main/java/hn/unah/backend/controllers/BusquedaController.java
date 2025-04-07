package hn.unah.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.dtos.BuscarReservasRequest;
import hn.unah.backend.dtos.ReservaResponse;
import hn.unah.backend.services.BusquedaService;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public class BusquedaController {

    @Autowired
    private BusquedaService reservaService;

    @PostMapping("/mostrar")
    public ResponseEntity<List<ReservaResponse>> mostrar(@RequestBody BuscarReservasRequest busquedaRequest){
        System.out.println(this.reservaService.mostrarReserva(busquedaRequest));
        return new ResponseEntity<>(this.reservaService.mostrarReserva(busquedaRequest), HttpStatus.OK);
    } 

    

}
