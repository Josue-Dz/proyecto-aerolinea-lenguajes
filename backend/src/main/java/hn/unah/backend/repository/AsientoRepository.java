package hn.unah.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.backend.models.Asiento;
import hn.unah.backend.models.Vuelo;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer>{
    public List<Asiento> findByVuelo(Vuelo vuelo);
}
