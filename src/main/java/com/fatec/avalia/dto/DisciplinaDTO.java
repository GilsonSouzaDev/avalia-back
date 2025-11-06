package com.fatec.avalia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {
    private long id;
    private String nome;
    private String cor;
    private Set<Long> professoresIds;
}
