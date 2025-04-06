package hn.unah.backend.models;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Reserva{

    @Id
    @Column(name ="codigo_reserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoReserva;

   @OneToMany(mappedBy = "reserva")
    private List<Boleto> boletos;

    @ManyToMany
    @JoinTable(
        name = "reservas_x_vuelos",
        joinColumns = @JoinColumn(name = "codigo_reserva"),
        inverseJoinColumns = @JoinColumn(name = "codigo_vuelo")
    )
    private List<Vuelo> vuelos;
}
