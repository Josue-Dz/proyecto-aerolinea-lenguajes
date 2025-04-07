package hn.unah.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.backend.dtos.AsientoDto;
import hn.unah.backend.dtos.AvionDto;
import hn.unah.backend.dtos.ClaseDto;
import hn.unah.backend.dtos.EstadoAsientoDto;
import hn.unah.backend.dtos.VueloDto;
import hn.unah.backend.models.Asiento;
import hn.unah.backend.models.Vuelo;
import hn.unah.backend.repository.AsientoRepository;
import hn.unah.backend.repository.VueloRepository;

@Service
public class AsientoService{
 
    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private VueloRepository vueloRepository;

    public List<AsientoDto> obtenerAsientoPorVuelo(int idVuelo){
        Vuelo vuelo = vueloRepository.findById(idVuelo).get();
        List<Asiento> asientos = asientoRepository.findByVuelo(vuelo);
        List<AsientoDto> asientoDtos = new ArrayList<>();

        VueloDto v = new VueloDto();
        v.setCodigoVuelo(vuelo.getCodigoVuelo());
        
        for (Asiento asiento : asientos) {
            AsientoDto a = new AsientoDto(); 
            a.setCodigoAsiento(asiento.getCodigoAsiento());
            a.setTarifa(asiento.getTarifa());
            a.setVuelo(v);

            ClaseDto c = new ClaseDto();
            c.setCodigoClase(asiento.getClase().getCodigoClase());
            a.setClase(c);

            AvionDto av = new AvionDto();
            av.setCodigoAvion(asiento.getAvion().getCodigoAvion());
            a.setAvion(av);

            EstadoAsientoDto e = new EstadoAsientoDto();
            e.setCodigoEstado(asiento.getEstadoAsiento().getCodigoEstado());
            a.setEstadoAsiento(e);

            asientoDtos.add(a);
        }
        return asientoDtos;
    } 
    
}
