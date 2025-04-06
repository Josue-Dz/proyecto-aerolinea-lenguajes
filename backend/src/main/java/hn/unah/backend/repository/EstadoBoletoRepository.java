package hn.unah.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.EstadoBoleto;

@Repository
public interface EstadoBoletoRepository extends JpaRepository<EstadoBoleto, Integer>{
    
    Optional<EstadoBoleto> findByNombre(String nombre);
}
