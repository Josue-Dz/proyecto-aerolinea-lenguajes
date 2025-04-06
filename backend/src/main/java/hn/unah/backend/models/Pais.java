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
@Table(name ="paises")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="codigo_pais")
    private int codigoPais;
    
    private String pais;

    @OneToMany(mappedBy = "pais")
    private List<Lugar> lugares;
}
