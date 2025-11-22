package com.fatec.avalia.service;

import com.fatec.avalia.dto.disciplina.DisciplinaRequestDTO;
import com.fatec.avalia.dto.disciplina.DisciplinaResponseDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public DisciplinaResponseDTO cadastrar(DisciplinaRequestDTO dto) {
        if (disciplinaRepository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new IllegalArgumentException("Já existe uma disciplina com este nome.");
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setCor(dto.getCor() == null || dto.getCor().isEmpty() ? gerarCorAleatoria() : dto.getCor());

        return toDTO(disciplinaRepository.save(disciplina));
    }

    public List<DisciplinaResponseDTO> listarTodas() {
        return disciplinaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {
        return disciplinaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    @Transactional
    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        disciplina.setNome(dto.getNome());

        if (dto.getCor() != null && !dto.getCor().isEmpty()) {
            disciplina.setCor(dto.getCor());
        }

        return toDTO(disciplinaRepository.save(disciplina));
    }

    public void excluir(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina não encontrada");
        }
        disciplinaRepository.deleteById(id);
    }

    private String gerarCorAleatoria() {
        Random random = new Random();
        return String.format("#%06x", random.nextInt(0xffffff + 1));
    }

    private DisciplinaResponseDTO toDTO(Disciplina entity) {
        DisciplinaResponseDTO dto = new DisciplinaResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCor(entity.getCor());

        if (entity.getPerguntas() != null) {
            List<Long> ids = entity.getPerguntas().stream()
                    .map(p -> p.getId())
                    .collect(Collectors.toList());
            dto.setPerguntasIds(ids);
        }
        return dto;
    }
}