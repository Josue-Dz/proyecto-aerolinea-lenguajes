package hn.unah.backend.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

  @Entity
  @Table(name = "estado_asiento")
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
public class EstadoAsiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_estado")
  private int codigoEstado;

  private String nombre;

  @OneToMany(mappedBy = "estadoAsiento")
  @JoinColumn(name ="codigo_asiento", referencedColumnName= "codigo_asiento")
   private  List<Asiento> asientos;

}
