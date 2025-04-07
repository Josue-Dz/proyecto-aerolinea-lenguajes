package hn.unah.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.backend.dtos.BuscarReservasRequest;
import hn.unah.backend.dtos.ReservaResponse;
import hn.unah.backend.models.Reserva;
import hn.unah.backend.models.Vuelo;
import hn.unah.backend.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaResponse mostrarReserva(BuscarReservasRequest buscarReservas) {

        List<Reserva> reservas = this.reservaRepository.findAll();
        final double PRECIO_X_KM = 0.16; // Centavos de dolar

        if (reservas != null) {
            for (Reserva reserva : reservas) {
                int ultimo = reserva.getVuelos().size() - 1;

                if ((reserva.getVuelos().get(0).getAeropuertoSalida().getLugar().getNombre()
                        .equals(buscarReservas.origen())) &&
                        (reserva.getVuelos().get(ultimo).getAeropuertoLlegada().getLugar().getNombre()
                                .equals(buscarReservas.destino()))) {

                    boolean a = reserva.getVuelos().get(0).getFechaHoraLlegada().toLocalDate()
                            .isBefore(buscarReservas.fechaSalida());
                    boolean b = reserva.getVuelos().get(0).getFechaHoraSalida().toLocalDate()
                            .isEqual(buscarReservas.fechaSalida());
                    boolean c = reserva.getVuelos().get(ultimo).getFechaHoraSalida().toLocalDate()
                            .isAfter(buscarReservas.fechaRegreso());
                    boolean d = reserva.getVuelos().get(ultimo).getFechaHoraSalida().toLocalDate()
                            .isEqual(buscarReservas.fechaRegreso());

                    if ((a || b) && (c || d)) {
                      
                        Double precio = 0.0;

                        // Recupera el primer y último vuelo de la reserva
                        Vuelo primerEscala = reserva.getVuelos().get(0);
                        Vuelo ultimaEscala = reserva.getVuelos().get(ultimo);
                        double distancia = calcularDistancia(primerEscala, ultimaEscala);
                        precio = (PRECIO_X_KM * distancia) * buscarReservas.cantidadViajeros();
                        System.out.println(distancia);

                        if ((reserva.getVuelos().get(0).getAeropuertoSalida()) != null
                                && (reserva.getVuelos().get(0).getAeropuertoSalida() != null)) {

                            String aeropuertoSalida = reserva.getVuelos().get(0).getAeropuertoSalida().getNombre() + ", "
                                    + reserva.getVuelos().get(0).getAeropuertoSalida().getLugar().getNombre();

                            String aeropuertoLlegada = reserva.getVuelos().get(0).getAeropuertoLlegada().getNombre() + ", "
                                    + reserva.getVuelos().get(ultimo).getAeropuertoLlegada().getLugar().getNombre();

                            LocalDateTime horaFechaLlegada = reserva.getVuelos().get(ultimo).getFechaHoraLlegada();
                            LocalDateTime horaFechaSalida = reserva.getVuelos().get(0).getFechaHoraSalida();
                            String estadoVuelo = reserva.getVuelos().get(0).getEstadoVuelo().getNombre();
                            int codigoReserva = reserva.getCodigoReserva();

                            ReservaResponse reservaResponse = new ReservaResponse(aeropuertoSalida, aeropuertoLlegada, estadoVuelo,
                                    horaFechaSalida, horaFechaLlegada, precio, codigoReserva);
                            return reservaResponse;
                        }

                    }
                }

            }
        }

        ReservaResponse reservaResponse = 
            new ReservaResponse("", "", "", LocalDateTime.now(), 
                                LocalDateTime.now(), PRECIO_X_KM, 0);
        return reservaResponse;
    }

    private double calcularDistancia(Vuelo primeraEscala, Vuelo ultimaEscala) {

        // Radio de la tierra en kilómetros
        final double R = 6371;

        // Latitud y longitud Aeropuerto de salida
        double lat1 = Math.toRadians(primeraEscala.getAeropuertoSalida().getLugar().getLatitud());
        double lon1 = Math.toRadians(primeraEscala.getAeropuertoSalida().getLugar().getLongitud());
        // Latitud y longitud Aeropuerto de llegada
        double lat2 = Math.toRadians(ultimaEscala.getAeropuertoLlegada().getLugar().getLatitud());
        double lon2 = Math.toRadians(ultimaEscala.getAeropuertoLlegada().getLugar().getLongitud());

        // Diferencias de latitud y longitud
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Fórmula del haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = R * c; // Distancia en kilómetros

        return Math.round(distancia);
    }
}
