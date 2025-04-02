package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Lugar;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Integer>{

}