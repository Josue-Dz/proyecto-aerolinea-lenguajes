package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer>{

}
