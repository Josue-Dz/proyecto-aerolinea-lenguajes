package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Asiento;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer>{

}
