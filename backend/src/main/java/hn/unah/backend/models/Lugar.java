package hn.unah.backend.models;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
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

    private BigDecimal latitud;

    private BigDecimal longitud;

    @OneToOne(mappedBy = "lugar")
    private Aeropuerto aeropuertos;

    @ManyToOne
    @JoinColumn(name = "codigo_pais")
    private Pais pais;

}
