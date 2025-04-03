package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.TipoAvion;

@Repository
public interface TipoAvionRepository extends JpaRepository<TipoAvion, Integer>{

}
