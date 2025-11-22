package com.fatec.avalia.dto.professor;

import com.fatec.avalia.enums.PerfilProfessor; // Certifique-se do package correto do Enum
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDTO {

    @NotNull(message = "O código do professor é obrigatório")
    private Long codigo; // Seu novo campo

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "O perfil do professor é obrigatório")
    private PerfilProfessor perfilProfessor;

    // Recebe apenas os IDs das disciplinas para vincular
    private List<Long> disciplinasIds;
}