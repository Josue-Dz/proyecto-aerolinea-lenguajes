package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer>{

}
