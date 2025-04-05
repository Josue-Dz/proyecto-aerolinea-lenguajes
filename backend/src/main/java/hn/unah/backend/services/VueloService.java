package hn.unah.backend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.springframework.web.server.ResponseStatusException;

import hn.unah.backend.models.Boleto;
import hn.unah.backend.models.Usuario;
import hn.unah.backend.models.Vuelo;
import hn.unah.backend.repository.UsuarioRepository;
import hn.unah.backend.repository.BoletoRepository;

@Service
public class VueloService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Vuelo> obtenerVuelosReservadosFuturos(String correoUsuario) {
        // Obtener el usuario por correo
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        // Obtener la fecha actual
        LocalDateTime ahora = LocalDateTime.now();

        // Buscar los boletos con vuelos futuros
        List<Boleto> boletosFuturos = boletoRepository.findByUsuarioAndVueloFechaHoraSalidaAfter(usuario, ahora);

        // Extraer solo los vuelos (puedes devolver la lista de vuelos)
        List<Vuelo> vuelosFuturos = boletosFuturos.stream()
                .map(Boleto::getVuelo)
                .collect(Collectors.toList());

        return vuelosFuturos;
    }

    public Map<String, List<Vuelo>> obtenerHistorialDeVuelos(String correoUsuario) {
        List<Boleto> boletos = boletoRepository.findByUsuarioCorreo(correoUsuario);

        List<Vuelo> vuelosPasados = new ArrayList<>();
        List<Vuelo> vuelosFuturos = new ArrayList<>();

        LocalDateTime ahora = LocalDateTime.now();

        for (Boleto boleto : boletos) {
            Vuelo vuelo = boleto.getVuelo();
            if (vuelo.getFechaHoraSalida().isBefore(ahora)) {
                vuelosPasados.add(vuelo);
            } else {
                vuelosFuturos.add(vuelo);
            }
        }

        Map<String, List<Vuelo>> historial = new HashMap<>();
        historial.put("vuelosPasados", vuelosPasados);
        historial.put("vuelosFuturos", vuelosFuturos);

        return historial;
    }

}