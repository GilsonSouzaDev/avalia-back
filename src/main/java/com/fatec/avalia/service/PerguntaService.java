package com.fatec.avalia.service;

import com.fatec.avalia.dto.alternativa.AlternativaResponseDTO;
import com.fatec.avalia.dto.alternativa.AlternativaTextoDTO;
import com.fatec.avalia.dto.disciplina.DisciplinaSimplesDTO;
import com.fatec.avalia.dto.pergunta.PerguntaRequestDTO;
import com.fatec.avalia.dto.pergunta.PerguntaResponseDTO;
import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.PerguntaRepository;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final CloudStorageService cloudStorageService;

    public PerguntaService(PerguntaRepository perguntaRepository,
                           DisciplinaRepository disciplinaRepository,
                           ProfessorRepository professorRepository,
                           CloudStorageService cloudStorageService) {
        this.perguntaRepository = perguntaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
        this.cloudStorageService = cloudStorageService;
    }

    @Transactional
    public PerguntaResponseDTO cadastrar(PerguntaRequestDTO dto, MultipartFile imagem) {
        validarRespostaUnica(dto);

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Pergunta pergunta = new Pergunta();
        pergunta.setEnunciado(dto.getEnunciado());
        pergunta.setProfessor(professor);
        pergunta.setDisciplina(disciplina);

        if (imagem != null && !imagem.isEmpty()) {
            pergunta.setImagem(cloudStorageService.subirArquivo(imagem));
        }

        processarAlternativas(pergunta, dto.getAlternativas());

        return toDTO(perguntaRepository.save(pergunta));
    }

    @Transactional
    public PerguntaResponseDTO atualizar(Long id, PerguntaRequestDTO dto, MultipartFile imagem) {
        validarRespostaUnica(dto);

        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        pergunta.setEnunciado(dto.getEnunciado());

        if (!pergunta.getDisciplina().getId().equals(dto.getDisciplinaId())) {
            Disciplina novaDisciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            pergunta.setDisciplina(novaDisciplina);
        }

        // Se uma nova imagem foi enviada
        if (imagem != null && !imagem.isEmpty()) {
            // 1. Se já existia uma imagem antiga, apaga ela do Supabase
            if (pergunta.getImagem() != null) {
                cloudStorageService.deletarArquivo(pergunta.getImagem());
            }
            // 2. Sobe a nova
            pergunta.setImagem(cloudStorageService.subirArquivo(imagem));
        }

        pergunta.getAlternativas().clear();
        processarAlternativas(pergunta, dto.getAlternativas());

        return toDTO(perguntaRepository.save(pergunta));
    }

    @Transactional
    public void excluir(Long id) {
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        // Se a pergunta tem imagem, remove do storage antes de apagar do banco
        if (pergunta.getImagem() != null) {
            cloudStorageService.deletarArquivo(pergunta.getImagem());
        }

        perguntaRepository.delete(pergunta);
    }

    @Transactional(readOnly = true)
    public List<PerguntaResponseDTO> listarTodas() {
        return perguntaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PerguntaResponseDTO buscarPorId(Long id) {
        return perguntaRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<PerguntaResponseDTO> listarPorProfessor(Long professorId) {
        return perguntaRepository.findByProfessorId(professorId).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    private void validarRespostaUnica(PerguntaRequestDTO dto) {
        long quantidadeCorretas = dto.getAlternativas().stream()
                .filter(AlternativaTextoDTO::isCorreta).count();
        if (quantidadeCorretas != 1) {
            throw new RuntimeException("Erro: A pergunta deve ter exatamente uma alternativa correta.");
        }
    }

    private void processarAlternativas(Pergunta pergunta, List<AlternativaTextoDTO> dtos) {
        for (AlternativaTextoDTO altDto : dtos) {
            Alternativa alt = new Alternativa();
            alt.setTexto(altDto.getTexto());
            alt.setCorreta(altDto.isCorreta());
            alt.setPergunta(pergunta);
            pergunta.getAlternativas().add(alt);
        }
    }

    private PerguntaResponseDTO toDTO(Pergunta entity) {
        PerguntaResponseDTO dto = new PerguntaResponseDTO();
        dto.setId(entity.getId());
        dto.setEnunciado(entity.getEnunciado());
        dto.setImagem(entity.getImagem());
        dto.setProfessorId(entity.getProfessor().getId());
        if (entity.getDisciplina() != null) {
            dto.setDisciplina(new DisciplinaSimplesDTO(
                    entity.getDisciplina().getId(), entity.getDisciplina().getNome(), entity.getDisciplina().getCor()));
        }
        dto.setAlternativas(entity.getAlternativas().stream()
                .map(a -> new AlternativaResponseDTO(a.getId(), a.getTexto(), a.isCorreta(), entity.getId()))
                .collect(Collectors.toList()));
        return dto;
    }
}