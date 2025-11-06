package com.fatec.avalia.service;

import com.fatec.avalia.dto.ProfessorDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;

    public ProfessorService(ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository) {
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<ProfessorDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProfessorDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return toDTO(professor);
    }

    public ProfessorDTO salvar(ProfessorDTO professorDTO) {
        Professor professor = toEntity(professorDTO);
        Professor salvo = professorRepository.save(professor);
        return toDTO(salvo);
    }

    public ProfessorDTO atualizar(Long id, ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha());
        professor.setPerfilProfessor(professorDTO.getPerfilProfessor());

        Professor atualizado = professorRepository.save(professor);
        return new ProfessorDTO(atualizado);
    }

    public void excluir(Long id) {
        professorRepository.deleteById(id);
    }

    // Converte Entity para DTI
    private ProfessorDTO toDTO(Professor professor) {
        return new ProfessorDTO(professor);
    }

    // Converter DTO para Entity
    private Professor toEntity(ProfessorDTO professorDTO) {
        Professor professor = new Professor();

        professor.setNome(professorDTO.getNome());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha());
        professor.setPerfilProfessor(professorDTO.getPerfilProfessor());

        Set<Disciplina> disciplinas = new HashSet<>();
        if (professorDTO.getDisciplinasIds() != null) {
            for (Long id : professorDTO.getDisciplinasIds()) {
                Disciplina disciplina = disciplinaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Disciplina " + id + " não foi encontrada"));
                disciplinas.add(disciplina);
            }
        }

        professor.setDisciplinas(disciplinas);
        return professor;
    }

}

