package com.fatec.avalia.service;

import com.fatec.avalia.dto.disciplina.DisciplinaSimplesDTO;
import com.fatec.avalia.dto.professor.ProfessorRequestDTO;
import com.fatec.avalia.dto.professor.ProfessorResponseDTO;
import com.fatec.avalia.dto.professor.ProfessorUpdateDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.PerguntaRepository;
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
    private final PerguntaRepository perguntaRepository;

    public ProfessorService(ProfessorRepository professorRepository,
                            DisciplinaRepository disciplinaRepository,
                            PerguntaRepository perguntaRepository) {
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.perguntaRepository = perguntaRepository;
    }

    // --- AUTENTICAÇÃO ---

    @Transactional(readOnly = true)
    public ProfessorResponseDTO autenticar(String email, String senha) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        // Comparação de senha (em produção usar BCrypt)
        if (!professor.getSenha().equals(senha)) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return toDTO(professor);
    }

    @Transactional
    public void redefinirSenha(String email, String novaSenha) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado no sistema."));

        professor.setSenha(novaSenha);
        professorRepository.save(professor);
    }

    // --- CRUD ---

    @Transactional
    public ProfessorResponseDTO salvar(ProfessorRequestDTO dto) {
        if (professorRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setSenha(dto.getSenha());
        professor.setPerfilProfessor(dto.getPerfilProfessor());

        if (dto.getDisciplinasIds() != null) {
            professor.setDisciplinas(new HashSet<>(disciplinaRepository.findAllById(dto.getDisciplinasIds())));
        }

        return toDTO(professorRepository.save(professor));
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfessorResponseDTO buscarPorId(Long id) {
        return professorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    @Transactional
    public ProfessorResponseDTO atualizar(Long id, ProfessorUpdateDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (dto.getDisciplinasIds() != null) {
            Set<Long> atuais = professor.getDisciplinas().stream()
                    .map(Disciplina::getId)
                    .collect(Collectors.toSet());

            // Regra: Se a disciplina foi removida, deletamos as perguntas atreladas ao ID do professor e da disciplina
            atuais.stream()
                    .filter(oldId -> !dto.getDisciplinasIds().contains(oldId))
                    .forEach(removidaId -> perguntaRepository.deleteByProfessorIdAndDisciplinaId(professor.getId(), removidaId));

            professor.setDisciplinas(new HashSet<>(disciplinaRepository.findAllById(dto.getDisciplinasIds())));
        }

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            professor.setSenha(dto.getSenha());
        }
        professor.setPerfilProfessor(dto.getPerfilProfessor());

        return toDTO(professorRepository.save(professor));
    }

    @Transactional
    public void excluir(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado");
        }
        // O cascade na entidade cuidará das perguntas, mas a limpeza explícita é mais segura
        perguntaRepository.deleteByProfessorId(id);
        professorRepository.deleteById(id);
    }

    // --- CONVERSOR ---

    private ProfessorResponseDTO toDTO(Professor entity) {
        ProfessorResponseDTO dto = new ProfessorResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setPerfilProfessor(entity.getPerfilProfessor());

        if (entity.getDisciplinas() != null) {
            dto.setDisciplinas(entity.getDisciplinas().stream()
                    .map(d -> new DisciplinaSimplesDTO(d.getId(), d.getNome(), d.getCor()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}