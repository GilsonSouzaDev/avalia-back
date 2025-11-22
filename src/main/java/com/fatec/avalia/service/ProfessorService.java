package com.fatec.avalia.service;

import com.fatec.avalia.dto.disciplina.DisciplinaSimplesDTO;
import com.fatec.avalia.dto.professor.ProfessorRequestDTO;
import com.fatec.avalia.dto.professor.ProfessorResponseDTO;
import com.fatec.avalia.dto.professor.ProfessorUpdateDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // --- AUTENTICAÇÃO E SENHA ---

    public ProfessorResponseDTO autenticar(String email, String senha) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        // Nota: Em produção, compare hashes (ex: BCrypt)
        if (!professor.getSenha().equals(senha)) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return toDTO(professor);
    }

    @Transactional
    public void redefinirSenha(String email, String novaSenha) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado no sistema."));

        professor.setSenha(novaSenha); // Nota: Em produção, criptografar antes de salvar
        professorRepository.save(professor);
    }

    // --- CRUD ---

    @Transactional
    public ProfessorResponseDTO salvar(ProfessorRequestDTO dto) {
        if (professorRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (professorRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Código de professor já existente.");
        }

        Professor professor = new Professor();
        professor.setCodigo(dto.getCodigo());
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setSenha(dto.getSenha());
        professor.setPerfilProfessor(dto.getPerfilProfessor());

        if (dto.getDisciplinasIds() != null && !dto.getDisciplinasIds().isEmpty()) {
            Set<Disciplina> disciplinas = new HashSet<>(disciplinaRepository.findAllById(dto.getDisciplinasIds()));
            professor.setDisciplinas(disciplinas);
        }

        return toDTO(professorRepository.save(professor));
    }

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        return professorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    @Transactional
    public ProfessorResponseDTO atualizar(Long id, ProfessorUpdateDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setPerfilProfessor(dto.getPerfilProfessor());

        if (dto.getSenha() != null && !dto.getSenha().trim().isEmpty()) {
            professor.setSenha(dto.getSenha());
        }

        if (dto.getDisciplinasIds() != null) {
            Set<Disciplina> disciplinas = new HashSet<>(disciplinaRepository.findAllById(dto.getDisciplinasIds()));
            professor.setDisciplinas(disciplinas);
        }

        return toDTO(professorRepository.save(professor));
    }

    public void excluir(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado");
        }
        professorRepository.deleteById(id);
    }

    // --- CONVERSOR ---

    private ProfessorResponseDTO toDTO(Professor entity) {
        ProfessorResponseDTO dto = new ProfessorResponseDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setPerfilProfessor(entity.getPerfilProfessor());

        if (entity.getDisciplinas() != null) {
            List<DisciplinaSimplesDTO> disciplinasDTO = entity.getDisciplinas().stream()
                    .map(d -> new DisciplinaSimplesDTO(d.getId(), d.getNome(), d.getCor()))
                    .collect(Collectors.toList());
            dto.setDisciplinas(disciplinasDTO);
        }
        return dto;
    }
}