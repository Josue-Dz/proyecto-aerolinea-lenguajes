package hn.unah.backend.models;



import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservas")
@Entity
public class Reserva implements Serializable{

    @Id
    @Column(name ="codigo_reserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoReserva;

   @OneToMany(mappedBy ="reservas", cascade = CascadeType.ALL)
    private List<ReservaVuelos> vuelos;
    
}
