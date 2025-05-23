package hn.unah.backend.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_tarjeta")
    private int codigoTarjeta;

    @Column(name = "numero")
    private int numero;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario", nullable = false)
    private Usuario usuario;

}
