package hn.unah.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.backend.dtos.BuscarReservasRequest;
import hn.unah.backend.dtos.ReservaResponse;
import hn.unah.backend.services.BusquedaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public class BusquedaController {

    @Autowired
    private BusquedaService reservaService;

      @Operation(
        summary = "Buscar reservas de vuelos",
        description = "Este endpoint permite buscar vuelos disponibles según el origen, destino, fechas de salida y regreso, y cantidad de viajeros. " +
                      "Devuelve los vuelos que coincidan con los criterios. Si no se encuentra ningún vuelo, la lista estará vacía."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente. Se devuelven los vuelos disponibles."),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud."),
        @ApiResponse(responseCode = "500", description = "Error interno al realizar la búsqueda.")
    })
    @PostMapping("/mostrar")
    public ResponseEntity<List<ReservaResponse>> mostrar(@RequestBody BuscarReservasRequest busquedaRequest){
        System.out.println(this.reservaService.mostrarReserva(busquedaRequest));
        return new ResponseEntity<>(this.reservaService.mostrarReserva(busquedaRequest), HttpStatus.OK);
    } 

    

}
