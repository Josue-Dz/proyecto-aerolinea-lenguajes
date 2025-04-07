package hn.unah.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.backend.dtos.AeropuertoDto;
import hn.unah.backend.dtos.LugarDto;
import hn.unah.backend.dtos.PaisDto;
import hn.unah.backend.models.Aeropuerto;
import hn.unah.backend.models.Lugar;
import hn.unah.backend.models.Pais;
import hn.unah.backend.repository.AeropuertoRepository;

@Service
public class AeropuertoService {
    
    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    public List<AeropuertoDto> obtenerTodosLosAeropuertos() {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
        List<AeropuertoDto> aeropuertoDtos = new ArrayList<>();
    
        for (Aeropuerto aeropuerto : aeropuertos) {
            AeropuertoDto dto = new AeropuertoDto();
            dto.setCodigoAeropuerto(aeropuerto.getCodigoAeropuerto());
            dto.setNombre(aeropuerto.getNombre());
            dto.setCapacidadAviones(aeropuerto.getCapacidadAviones());
    
            if (aeropuerto.getLugar() != null) {
                Lugar lugar = aeropuerto.getLugar();
                LugarDto lugarDto = new LugarDto();
                lugarDto.setCodigoLugar(lugar.getCodigoLugar());
                lugarDto.setNombre(lugar.getNombre());
                lugarDto.setLatitud(lugar.getLatitud());
                lugarDto.setLongitud(lugar.getLongitud());
    
                if (lugar.getPais() != null) {
                    Pais pais = lugar.getPais();
                    PaisDto paisDto = new PaisDto();
                    paisDto.setCodigoPais(pais.getCodigoPais());
                    paisDto.setPais(pais.getPais());
                    lugarDto.setPais(paisDto);
                }
    
                dto.setLugar(lugarDto);
            }
    
            aeropuertoDtos.add(dto);
        }
    
        return aeropuertoDtos;
    }

    
}

