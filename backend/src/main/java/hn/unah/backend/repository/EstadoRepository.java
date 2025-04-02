package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
