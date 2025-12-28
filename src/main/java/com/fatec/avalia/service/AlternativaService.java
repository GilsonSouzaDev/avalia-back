package com.fatec.avalia.service;

import com.fatec.avalia.dto.alternativa.AlternativaRequestDTO;
import com.fatec.avalia.dto.alternativa.AlternativaResponseDTO;
import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.repository.AlternativaRepository;
import com.fatec.avalia.repository.PerguntaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativaService {

    private final AlternativaRepository alternativaRepository;
    private final PerguntaRepository perguntaRepository;

    public AlternativaService(AlternativaRepository alternativaRepository, PerguntaRepository perguntaRepository) {
        this.alternativaRepository = alternativaRepository;
        this.perguntaRepository = perguntaRepository;
    }

    @Transactional
    public AlternativaResponseDTO salvar(AlternativaRequestDTO dto) {
        Pergunta pergunta = perguntaRepository.findById(dto.getPerguntaId())
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        Alternativa alternativa = new Alternativa();
        alternativa.setTexto(dto.getTexto());
        alternativa.setPergunta(pergunta);

        return toDTO(alternativaRepository.save(alternativa));
    }

    public List<AlternativaResponseDTO> listarPorPergunta(Long perguntaId) {
        return alternativaRepository.findByPerguntaId(perguntaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlternativaResponseDTO atualizar(Long id, AlternativaRequestDTO dto) {
        Alternativa alternativa = alternativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));

        alternativa.setTexto(dto.getTexto());

        // Verifica se houve mudança de pergunta (caso raro, mas possível)
        if (!alternativa.getPergunta().getId().equals(dto.getPerguntaId())) {
            Pergunta novaPergunta = perguntaRepository.findById(dto.getPerguntaId())
                    .orElseThrow(() -> new RuntimeException("Nova pergunta não encontrada"));
            alternativa.setPergunta(novaPergunta);
        }

        return toDTO(alternativaRepository.save(alternativa));
    }

    public void excluir(Long id) {
        if (!alternativaRepository.existsById(id)) {
            throw new RuntimeException("Alternativa não encontrada");
        }
        alternativaRepository.deleteById(id);
    }

    private AlternativaResponseDTO toDTO(Alternativa entity) {
        // Corrigido: Agora passa os 4 parâmetros necessários para o construtor do DTO
        return new AlternativaResponseDTO(
                entity.getId(),
                entity.getTexto(),
                entity.isCorreta(), // Incluído o campo booleano
                entity.getPergunta().getId()
        );
    }
}