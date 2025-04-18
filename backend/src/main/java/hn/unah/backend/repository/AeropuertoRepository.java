package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Aeropuerto;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer>{

}
