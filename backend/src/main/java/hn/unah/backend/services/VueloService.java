package hn.unah.backend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.springframework.web.server.ResponseStatusException;

import hn.unah.backend.models.Boleto;
import hn.unah.backend.models.Reserva;
import hn.unah.backend.models.Usuario;
import hn.unah.backend.models.Vuelo;
import hn.unah.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import hn.unah.backend.repository.BoletoRepository;

import hn.unah.backend.repository.ReservaVueloRepository;

@Service
public class VueloService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    private ReservaVueloRepository reservaVueloRepository;

    @Autowired
    private BoletoRepository boletoRepository;
    

    @Transactional
public List<Vuelo> obtenerVuelosReservadosFuturos(String correoUsuario) {
    // Obtener el usuario por correo
    Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);

    // Verificar si el usuario es null
    if (usuario == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    // Obtener la fecha actual
    LocalDateTime ahora = LocalDateTime.now();

    // Obtener todos los boletos del usuario
    List<Boleto> boletos = boletoRepository.findByUsuario(usuario);

    // Lista para almacenar los vuelos futuros
    List<Vuelo> vuelosFuturos = new ArrayList<>();

    // Iterar sobre los boletos y obtener la reserva asociada
    for (Boleto boleto : boletos) {
        // Obtener la reserva asociada con el boleto
        Reserva reserva = boleto.getReserva();  // Aquí accedes a la reserva a través del boleto

        // Obtener los vuelos asociados a la reserva
        List<Vuelo> vuelos = reserva.getVuelos();  // Asegúrate de que la relación esté bien configurada en Reserva

        // Filtrar los vuelos con fecha futura
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFechaHoraSalida().isAfter(ahora)) {
                vuelosFuturos.add(vuelo);
            }
        }
    }

    return vuelosFuturos;
}


@Transactional
public Map<String, List<Vuelo>> obtenerHistorialDeVuelos(String correoUsuario) {
    // Obtener el usuario por correo
    Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);

    // Verificar si el usuario es null
    if (usuario == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    // Obtener la fecha actual
    LocalDateTime ahora = LocalDateTime.now();

    // Obtener todos los boletos del usuario
    List<Boleto> boletos = boletoRepository.findByUsuario(usuario);

    // Listas para almacenar los vuelos pasados y futuros
    List<Vuelo> vuelosPasados = new ArrayList<>();
    List<Vuelo> vuelosFuturos = new ArrayList<>();

    // Iterar sobre los boletos y obtener la reserva asociada
    for (Boleto boleto : boletos) {
        // Obtener la reserva asociada con el boleto
        Reserva reserva = boleto.getReserva();  // Aquí accedes a la reserva a través del boleto

        // Obtener los vuelos asociados a la reserva
        List<Vuelo> vuelos = reserva.getVuelos();  // Asegúrate de que la relación esté bien configurada en Reserva

        // Filtrar los vuelos pasados y futuros
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFechaHoraSalida().isBefore(ahora)) {
                vuelosPasados.add(vuelo);
            } else {
                vuelosFuturos.add(vuelo);
            }
        }
    }

    // Crear un mapa con los vuelos pasados y futuros
    Map<String, List<Vuelo>> historial = new HashMap<>();
    historial.put("vuelosPasados", vuelosPasados);
    historial.put("vuelosFuturos", vuelosFuturos);

    return historial;
}

}