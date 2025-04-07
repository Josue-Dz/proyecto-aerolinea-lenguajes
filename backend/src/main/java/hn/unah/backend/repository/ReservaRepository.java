package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
}
