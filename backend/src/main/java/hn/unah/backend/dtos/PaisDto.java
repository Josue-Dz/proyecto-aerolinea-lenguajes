package hn.unah.backend.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaisDto {

    private int codigoPais;

    private String pais;

    private List<LugarDto> lugares;
    
}
