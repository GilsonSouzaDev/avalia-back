package com.fatec.avalia.service;

import com.fatec.avalia.dto.alternativa.AlternativaResponseDTO;
import com.fatec.avalia.dto.disciplina.DisciplinaSimplesDTO;
import com.fatec.avalia.dto.alternativa.AlternativaTextoDTO;
import com.fatec.avalia.dto.pergunta.PerguntaRequestDTO;
import com.fatec.avalia.dto.pergunta.PerguntaResponseDTO;
import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.PerguntaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public PerguntaService(PerguntaRepository perguntaRepository, DisciplinaRepository disciplinaRepository) {
        this.perguntaRepository = perguntaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public PerguntaResponseDTO cadastrar(PerguntaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Pergunta pergunta = new Pergunta();
        pergunta.setEnunciado(dto.getEnunciado());
        pergunta.setCodigoProfessor(dto.getCodigoProfessor());
        pergunta.setDisciplina(disciplina);

        if (dto.getAlternativas() != null) {
            List<Alternativa> listaAlternativas = new ArrayList<>();
            for (AlternativaTextoDTO altDto : dto.getAlternativas()) {
                Alternativa alt = new Alternativa();
                alt.setTexto(altDto.getTexto());
                alt.setPergunta(pergunta);
                listaAlternativas.add(alt);
            }
            pergunta.setAlternativas(listaAlternativas);
        }

        return toDTO(perguntaRepository.save(pergunta));
    }

    public List<PerguntaResponseDTO> listarTodas() {
        return perguntaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PerguntaResponseDTO buscarPorId(Long id) {
        return perguntaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }

    public List<PerguntaResponseDTO> listarPorProfessor(Long codigoProfessor) {
        return perguntaRepository.findByCodigoProfessor(codigoProfessor).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PerguntaResponseDTO atualizar(Long id, PerguntaRequestDTO dto) {
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        pergunta.setEnunciado(dto.getEnunciado());

        if (!pergunta.getDisciplina().getId().equals(dto.getDisciplinaId())) {
            Disciplina novaDisciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            pergunta.setDisciplina(novaDisciplina);
        }

        // Atualização de alternativas (estratégia: remove tudo e recria)
        // Graças ao orphanRemoval=true na Entidade, isso limpa o banco corretamente
        if (dto.getAlternativas() != null) {
            pergunta.getAlternativas().clear();
            for (AlternativaTextoDTO altDto : dto.getAlternativas()) {
                Alternativa alt = new Alternativa();
                alt.setTexto(altDto.getTexto());
                alt.setPergunta(pergunta);
                pergunta.getAlternativas().add(alt);
            }
        }

        return toDTO(perguntaRepository.save(pergunta));
    }

    public void excluir(Long id) {
        if (!perguntaRepository.existsById(id)) {
            throw new RuntimeException("Pergunta não encontrada");
        }
        perguntaRepository.deleteById(id);
    }

    private PerguntaResponseDTO toDTO(Pergunta entity) {
        PerguntaResponseDTO dto = new PerguntaResponseDTO();
        dto.setId(entity.getId());
        dto.setEnunciado(entity.getEnunciado());
        dto.setCodigoProfessor(entity.getCodigoProfessor());

        if (entity.getDisciplina() != null) {
            dto.setDisciplina(new DisciplinaSimplesDTO(
                    entity.getDisciplina().getId(),
                    entity.getDisciplina().getNome(),
                    entity.getDisciplina().getCor()
            ));
        }

        if (entity.getAlternativas() != null) {
            List<AlternativaResponseDTO> alts = entity.getAlternativas().stream()
                    .map(a -> new AlternativaResponseDTO(a.getId(), a.getTexto(), entity.getId()))
                    .collect(Collectors.toList());
            dto.setAlternativas(alts);
        }
        return dto;
    }
}