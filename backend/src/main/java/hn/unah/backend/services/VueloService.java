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

import hn.unah.backend.dtos.AeropuertoDto;
import hn.unah.backend.dtos.EstadoVueloDto;
import hn.unah.backend.dtos.LugarDto;
import hn.unah.backend.dtos.VueloDto;
import hn.unah.backend.models.Boleto;
import hn.unah.backend.models.Reserva;
import hn.unah.backend.models.Usuario;
import hn.unah.backend.models.Vuelo;
import hn.unah.backend.repository.UsuarioRepository;
import hn.unah.backend.repository.VueloRepository;
import jakarta.transaction.Transactional;
import hn.unah.backend.repository.BoletoRepository;


@Service
public class VueloService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private BoletoRepository boletoRepository;

    @Transactional
    public List<VueloDto> obtenerVuelosReservadosFuturos(int idUsuario) {
        // Obtener el usuario por correo
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        // Verificar si el usuario es null
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        // Obtener la fecha actual
        LocalDateTime ahora = LocalDateTime.now();

        // Obtener todos los boletos del usuario
        List<Boleto> boletos = boletoRepository.findByUsuario(usuario);

        // Lista para almacenar los vuelos futuros
        List<VueloDto> vuelosFuturos = new ArrayList<>();

        // Iterar sobre los boletos y obtener la reserva asociada
        for (Boleto boleto : boletos) {
            // Obtener la reserva asociada con el boleto
            Reserva reserva = boleto.getReserva(); // Aquí accedes a la reserva a través del boleto

            // Obtener los vuelos asociados a la reserva
            List<Vuelo> vuelos = reserva.getVuelos(); // Asegúrate de que la relación esté bien configurada en Reserva

            // Filtrar los vuelos con fecha futura
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getFechaHoraSalida().isAfter(ahora)) {
                    VueloDto v = new VueloDto();
                    v.setCodigoVuelo(vuelo.getCodigoVuelo());

                    AeropuertoDto aLlegada = new AeropuertoDto();
                    aLlegada.setCodigoAeropuerto(vuelo.getAeropuertoLlegada().getCodigoAeropuerto());
                    aLlegada.setNombre(vuelo.getAeropuertoLlegada().getNombre());

                    LugarDto lugarLlegada = new LugarDto();
                    lugarLlegada.setCodigoLugar(vuelo.getAeropuertoLlegada().getLugar().getCodigoLugar());
                    lugarLlegada.setNombre(vuelo.getAeropuertoLlegada().getLugar().getNombre());

                    aLlegada.setLugar(lugarLlegada);

                    v.setAeropuertoLlegada(aLlegada);

                    AeropuertoDto aSalida = new AeropuertoDto();
                    aSalida.setCodigoAeropuerto(vuelo.getAeropuertoSalida().getCodigoAeropuerto());
                    aSalida.setNombre(vuelo.getAeropuertoSalida().getNombre());

                    LugarDto lugarSalida = new LugarDto();
                    lugarSalida.setCodigoLugar(vuelo.getAeropuertoSalida().getLugar().getCodigoLugar());
                    lugarSalida.setNombre(vuelo.getAeropuertoSalida().getLugar().getNombre());

                    aSalida.setLugar(lugarSalida);

                    v.setAeropuertoSalida(aSalida);

                    v.setFechaHoraLlegada(vuelo.getFechaHoraLlegada());
                    v.setFechaHoraSalida(vuelo.getFechaHoraSalida());

                    EstadoVueloDto e = new EstadoVueloDto();
                    e.setCodigoEstado(vuelo.getEstadoVuelo().getCodigoEstado());
                    e.setNombre(vuelo.getEstadoVuelo().getNombre());
                    v.setEstado(e);

                    v.setDuracion(vuelo.getDuracion());

                    vuelosFuturos.add(v);
                }
            }
        }

        return vuelosFuturos;
    }

    @Transactional
    public Map<String, List<VueloDto>> obtenerHistorialDeVuelos(int idUsuario) {
        // Obtener el usuario por correo
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        // Verificar si el usuario es null
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        // Obtener la fecha actual
        LocalDateTime ahora = LocalDateTime.now();

        // Obtener todos los boletos del usuario
        List<Boleto> boletos = boletoRepository.findByUsuario(usuario);

        // Listas para almacenar los vuelos pasados y futuros
        List<VueloDto> vuelosPasados = new ArrayList<>();
        List<VueloDto> vuelosFuturos = new ArrayList<>();

        // Iterar sobre los boletos y obtener la reserva asociada
        for (Boleto boleto : boletos) {
            // Obtener la reserva asociada con el boleto
            Reserva reserva = boleto.getReserva(); // Aquí accedes a la reserva a través del boleto

            // Obtener los vuelos asociados a la reserva
            List<Vuelo> vuelos = reserva.getVuelos(); // Asegúrate de que la relación esté bien configurada en Reserva

            // Filtrar los vuelos pasados y futuros
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getFechaHoraSalida().isBefore(ahora)) {
                    VueloDto v = new VueloDto();
                    v.setCodigoVuelo(vuelo.getCodigoVuelo());

                    AeropuertoDto aLlegada = new AeropuertoDto();
                    aLlegada.setCodigoAeropuerto(vuelo.getAeropuertoLlegada().getCodigoAeropuerto());
                    aLlegada.setNombre(vuelo.getAeropuertoLlegada().getNombre());

                    LugarDto lugarLlegada = new LugarDto();
                    lugarLlegada.setCodigoLugar(vuelo.getAeropuertoLlegada().getLugar().getCodigoLugar());
                    lugarLlegada.setNombre(vuelo.getAeropuertoLlegada().getLugar().getNombre());

                    aLlegada.setLugar(lugarLlegada);

                    v.setAeropuertoLlegada(aLlegada);

                    AeropuertoDto aSalida = new AeropuertoDto();
                    aSalida.setCodigoAeropuerto(vuelo.getAeropuertoSalida().getCodigoAeropuerto());
                    aSalida.setNombre(vuelo.getAeropuertoSalida().getNombre());

                    LugarDto lugarSalida = new LugarDto();
                    lugarSalida.setCodigoLugar(vuelo.getAeropuertoSalida().getLugar().getCodigoLugar());
                    lugarSalida.setNombre(vuelo.getAeropuertoSalida().getLugar().getNombre());

                    aSalida.setLugar(lugarSalida);

                    v.setAeropuertoSalida(aSalida);

                    v.setFechaHoraLlegada(vuelo.getFechaHoraLlegada());
                    v.setFechaHoraSalida(vuelo.getFechaHoraSalida());

                    EstadoVueloDto e = new EstadoVueloDto();
                    e.setCodigoEstado(vuelo.getEstadoVuelo().getCodigoEstado());
                    e.setNombre(vuelo.getEstadoVuelo().getNombre());
                    v.setEstado(e);

                    v.setDuracion(vuelo.getDuracion());

                    vuelosPasados.add(v);
                } else {
                    VueloDto v = new VueloDto();
                    v.setCodigoVuelo(vuelo.getCodigoVuelo());

                    AeropuertoDto aLlegada = new AeropuertoDto();
                    aLlegada.setCodigoAeropuerto(vuelo.getAeropuertoLlegada().getCodigoAeropuerto());
                    aLlegada.setNombre(vuelo.getAeropuertoLlegada().getNombre());

                    LugarDto lugarLlegada = new LugarDto();
                    lugarLlegada.setCodigoLugar(vuelo.getAeropuertoLlegada().getLugar().getCodigoLugar());
                    lugarLlegada.setNombre(vuelo.getAeropuertoLlegada().getLugar().getNombre());

                    aLlegada.setLugar(lugarLlegada);

                    v.setAeropuertoLlegada(aLlegada);

                    AeropuertoDto aSalida = new AeropuertoDto();
                    aSalida.setCodigoAeropuerto(vuelo.getAeropuertoSalida().getCodigoAeropuerto());
                    aSalida.setNombre(vuelo.getAeropuertoSalida().getNombre());

                    LugarDto lugarSalida = new LugarDto();
                    lugarSalida.setCodigoLugar(vuelo.getAeropuertoSalida().getLugar().getCodigoLugar());
                    lugarSalida.setNombre(vuelo.getAeropuertoSalida().getLugar().getNombre());

                    aSalida.setLugar(lugarSalida);

                    v.setAeropuertoSalida(aSalida);

                    v.setFechaHoraLlegada(vuelo.getFechaHoraLlegada());
                    v.setFechaHoraSalida(vuelo.getFechaHoraSalida());

                    EstadoVueloDto e = new EstadoVueloDto();
                    e.setCodigoEstado(vuelo.getEstadoVuelo().getCodigoEstado());
                    e.setNombre(vuelo.getEstadoVuelo().getNombre());
                    v.setEstado(e);

                    v.setDuracion(vuelo.getDuracion());

                    vuelosFuturos.add(v);
                }
            }
        }

        // Crear un mapa con los vuelos pasados y futuros
        Map<String, List<VueloDto>> historial = new HashMap<>();
        historial.put("vuelosPasados", vuelosPasados);
        historial.put("vuelosFuturos", vuelosFuturos);

        return historial;
    }

    @Transactional
    public VueloDto obtenerVueloPorId(int idVuelo) {
        Vuelo vuelo = vueloRepository.findById(idVuelo).get();

        VueloDto v = new VueloDto();
        v.setCodigoVuelo(vuelo.getCodigoVuelo());

        AeropuertoDto aLlegada = new AeropuertoDto();
        aLlegada.setCodigoAeropuerto(vuelo.getAeropuertoLlegada().getCodigoAeropuerto());
        aLlegada.setNombre(vuelo.getAeropuertoLlegada().getNombre());

        LugarDto lugarLlegada = new LugarDto();
        lugarLlegada.setCodigoLugar(vuelo.getAeropuertoLlegada().getLugar().getCodigoLugar());
        lugarLlegada.setNombre(vuelo.getAeropuertoLlegada().getLugar().getNombre());

        aLlegada.setLugar(lugarLlegada);

        v.setAeropuertoLlegada(aLlegada);

        AeropuertoDto aSalida = new AeropuertoDto();
        aSalida.setCodigoAeropuerto(vuelo.getAeropuertoSalida().getCodigoAeropuerto());
        aSalida.setNombre(vuelo.getAeropuertoSalida().getNombre());

        LugarDto lugarSalida = new LugarDto();
        lugarSalida.setCodigoLugar(vuelo.getAeropuertoSalida().getLugar().getCodigoLugar());
        lugarSalida.setNombre(vuelo.getAeropuertoSalida().getLugar().getNombre());

        aSalida.setLugar(lugarSalida);

        v.setAeropuertoSalida(aSalida);

        v.setFechaHoraLlegada(vuelo.getFechaHoraLlegada());
        v.setFechaHoraSalida(vuelo.getFechaHoraSalida());

        EstadoVueloDto e = new EstadoVueloDto();
        e.setCodigoEstado(vuelo.getEstadoVuelo().getCodigoEstado());
        e.setNombre(vuelo.getEstadoVuelo().getNombre());
        v.setEstado(e);

        v.setDuracion(vuelo.getDuracion());

        return v;
    }

}