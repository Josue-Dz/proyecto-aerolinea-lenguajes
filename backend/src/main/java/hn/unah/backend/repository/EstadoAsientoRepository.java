package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.EstadoAsiento;

@Repository
public interface EstadoAsientoRepository extends JpaRepository<EstadoAsiento, Integer>{

}
