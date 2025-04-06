package hn.unah.backend.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="estados_boletos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoBoleto {
    @Id
    @Column(name ="codigo_estado")
    private int codigoEstado;

    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<Boleto> boletos;

}