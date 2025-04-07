package hn.unah.backend.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.backend.dtos.BuscarReservasRequest;
import hn.unah.backend.dtos.ReservaResponse;
import hn.unah.backend.models.Reserva;
import hn.unah.backend.models.Vuelo;
import hn.unah.backend.repository.ReservaRepository;

@Service
public class BusquedaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<ReservaResponse> mostrarReserva(BuscarReservasRequest buscarReservas) {

        List<Reserva> reservas = this.reservaRepository.findAll();
        List<ReservaResponse> reservasEncontradas = new LinkedList<>();
        final double PRECIO_X_KM = 0.16; // Centavos de dolar

        if (reservas != null) {
            for (Reserva reserva : reservas) {
                int ultimo = reserva.getVuelos().size() - 1;

                if ((reserva.getVuelos().get(0).getAeropuertoSalida().getLugar().getCodigoLugar() ==
                        buscarReservas.origen()) &&
                        (reserva.getVuelos().get(ultimo).getAeropuertoLlegada().getLugar().getCodigoLugar() ==
                                buscarReservas.destino())) {

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
                        
                            reservasEncontradas.add(reservaResponse);
                            
                        }
                        
                    }
                    
                }

            }
            return reservasEncontradas;
        }

        ReservaResponse reservaResponse = 
            new ReservaResponse("", "", "", LocalDateTime.now(), 
                                LocalDateTime.now(), PRECIO_X_KM, 0);
        reservasEncontradas.add(reservaResponse);
        return reservasEncontradas;
    }

    private double calcularDistancia(Vuelo primeraEscala, Vuelo ultimaEscala) {

        // Radio de la tierra en kilómetros
        final double R = 6371;

        // Obtener latitudes y longitudes 
        BigDecimal lat1BD = primeraEscala.getAeropuertoSalida().getLugar().getLatitud();
        BigDecimal lon1BD = primeraEscala.getAeropuertoSalida().getLugar().getLongitud();
        BigDecimal lat2BD = ultimaEscala.getAeropuertoLlegada().getLugar().getLatitud();
        BigDecimal lon2BD = ultimaEscala.getAeropuertoLlegada().getLugar().getLongitud();

        // Convertir a radianes (usando double)
        double lat1 = Math.toRadians(lat1BD.doubleValue());
        double lon1 = Math.toRadians(lon1BD.doubleValue());
        double lat2 = Math.toRadians(lat2BD.doubleValue());
        double lon2 = Math.toRadians(lon2BD.doubleValue());

        // Diferencias de latitud y longitud
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Fórmula del haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = R * c;

        // Retornar la distancia redondeada
        return Math.round(distancia);
    }
}


