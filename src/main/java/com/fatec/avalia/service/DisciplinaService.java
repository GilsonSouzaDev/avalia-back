package com.fatec.avalia.service;

import com.fatec.avalia.dto.DisciplinaDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.Random;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public List<DisciplinaDTO> listarTodos() {
        return disciplinaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DisciplinaDTO buscarPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        return toDTO(disciplina);
    }

    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = toEntity(disciplinaDTO);

        if (disciplina.getCor() == null || disciplina.getCor().isEmpty()) {
            disciplina.setCor(gerarCorAleatoria());
        }

        Disciplina salvar = disciplinaRepository.save(disciplina);
        return toDTO(salvar);
    }

    private String gerarCorAleatoria() {
        Random aleatorio = new Random();
        int r = aleatorio.nextInt(256);
        int g = aleatorio.nextInt(256);
        int b = aleatorio.nextInt(256);
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public DisciplinaDTO atualizar(Long id, DisciplinaDTO disciplinaDTO) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não foi encontrada"));

        disciplinaExistente.setNome(disciplinaDTO.getNome());
        disciplinaExistente.setCor(disciplinaDTO.getCor());

        if (disciplinaDTO.getProfessoresIds() != null) {
            Set<Professor> professores = new HashSet<>();
            for (Long professorId : disciplinaDTO.getProfessoresIds()) {
                Professor professor = professorRepository.findById(professorId)
                        .orElseThrow(() -> new RuntimeException("Professor não foi encontrado"));
                professores.add(professor);
            }
            disciplinaExistente.setProfessores(professores);
        }

        Disciplina atualizado = disciplinaRepository.save(disciplinaExistente);
        return toDTO(atualizado);
    }

    public void excluir(Long id) {
        disciplinaRepository.deleteById(id);
    }

    private DisciplinaDTO toDTO(Disciplina disciplina) {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(disciplina.getId());
        disciplinaDTO.setNome(disciplina.getNome());
        disciplinaDTO.setCor(disciplina.getCor());
        disciplinaDTO.setProfessoresIds(
                disciplina.getProfessores().stream()
                        .map(Professor::getId)
                        .collect(Collectors.toSet())
        );
        return disciplinaDTO;
    }

    private Disciplina toEntity(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina();
        //disciplina.setId(disciplinaDTO.getId());
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCor(disciplinaDTO.getCor());

        if(disciplinaDTO.getProfessoresIds() != null) {
            Set<Professor> professores = new HashSet<>();
            for (Long professorId : disciplinaDTO.getProfessoresIds()) {
                Professor professor = professorRepository.findById(professorId)
                        .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
                professores.add(professor);
            }
            disciplina.setProfessores(professores);
        }
        return disciplina;
    }

}

