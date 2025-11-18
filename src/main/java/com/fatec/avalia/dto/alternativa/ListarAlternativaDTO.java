package com.fatec.avalia.dto.alternativa;

import com.fatec.avalia.entity.Alternativa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListarAlternativaDTO {
    private Long id;
    private String texto;
    private boolean correta;

    public ListarAlternativaDTO(Alternativa alternativa) {
        this.id = alternativa.getId();
        this.texto = alternativa.getTexto();
        this.correta = alternativa.isCorreta();
    }

}
