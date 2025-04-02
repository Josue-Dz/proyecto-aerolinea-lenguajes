package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Integer>{

}
