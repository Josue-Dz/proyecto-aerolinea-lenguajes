package hn.unah.backend.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hn.unah.backend.dtos.TarjetaDto;

import hn.unah.backend.models.Tarjeta;
import hn.unah.backend.models.Usuario;

import hn.unah.backend.repository.TarjetaRepository;
import hn.unah.backend.repository.UsuarioRepository;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarjeta guardarTarjeta(TarjetaDto tarjetaDto) {
        // Obtener el usuario autenticado desde el SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String correoUsuario = authentication.getName(); // Obtén el correo del usuario autenticado

        // Buscar al usuario en la base de datos por su correo
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // Verificar si la tarjeta ya existe (por número de tarjeta)
        Tarjeta tarjetaExistente = tarjetaRepository.findByNumero(tarjetaDto.getNumero());

        if (tarjetaExistente != null) {
            return null;
        }

        // Si la tarjeta no existe, creamos una nueva
       if (tarjetaDto.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
    throw new IllegalArgumentException("El saldo de la tarjeta no puede ser negativo.");
        }

        // Si la tarjeta no existe, creamos una nueva
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumero(tarjetaDto.getNumero());
        tarjeta.setNombre(tarjetaDto.getNombre());
        tarjeta.setCvv(tarjetaDto.getCvv());
        tarjeta.setFechaEmision(tarjetaDto.getFechaEmision());
        tarjeta.setFechaVencimiento(tarjetaDto.getFechaVencimiento());
        tarjeta.setSaldo(tarjetaDto.getSaldo());
        tarjeta.setUsuario(usuario);

        // Guardamos la nueva tarjeta
        return tarjetaRepository.save(tarjeta);
    }

  public List<Tarjeta> obtenerTarjetasPorUsuario(String correoUsuario) {
    Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
    if (usuario == null) {
        throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correoUsuario);
    }
    return tarjetaRepository.findByUsuario(usuario);
}


}
