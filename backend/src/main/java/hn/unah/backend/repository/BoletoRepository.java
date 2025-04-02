package hn.unah.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Integer>{

}
