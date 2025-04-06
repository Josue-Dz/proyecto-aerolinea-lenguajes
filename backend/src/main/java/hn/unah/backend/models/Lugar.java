package hn.unah.backend.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "lugares")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_lugar")
    private int codigoLugar;

    private String nombre;

    private double latitud;

    private double longitud;

    @OneToMany(mappedBy = "lugar")
    private List<Aeropuerto> aeropuertos;

    @ManyToOne
    @JoinColumn(name = "codigo_pais")
    private Pais pais;

}
