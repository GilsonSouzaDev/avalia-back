package com.fatec.avalia.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemErroDTO {
    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private List<String> detalhes; // Para erros de validação (Bean Validation)

    public MensagemErroDTO(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }
}