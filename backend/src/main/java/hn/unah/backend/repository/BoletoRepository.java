package hn.unah.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Boleto;
import hn.unah.backend.models.Usuario;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Integer>{

     List<Boleto> findByUsuarioAndVueloFechaHoraSalidaAfter(Usuario usuario, LocalDateTime horaSalida);
     List<Boleto> findByUsuarioCorreo(String correoUsuario);
}
