package hn.unah.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Tarjeta;
import hn.unah.backend.models.Usuario;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer>{

    Tarjeta findByNumero(Long numero);

    Optional<Tarjeta> findTopByUsuario(Usuario usuario);

    List<Tarjeta> findByUsuario(Usuario usuario);
}
