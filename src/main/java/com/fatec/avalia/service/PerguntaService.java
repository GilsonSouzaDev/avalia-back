package com.fatec.avalia.service;

import com.fatec.avalia.dto.alternativa.AlternativaCadastradaDTO;
import com.fatec.avalia.dto.pergunta.CadastrarPerguntaDTO;
import com.fatec.avalia.dto.pergunta.EditarPerguntaDTO;
import com.fatec.avalia.dto.pergunta.ListarPerguntaDTO;
import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.entity.Professor;
import com.fatec.avalia.repository.AlternativaRepository;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.PerguntaRepository;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlternativaRepository alternativaRepository;

    public PerguntaService (PerguntaRepository perguntaRepository, ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository, AlternativaRepository alternativaRepository) {
        this.perguntaRepository = perguntaRepository;
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.alternativaRepository = alternativaRepository;
    }

    public ListarPerguntaDTO cadastrarPergunta(CadastrarPerguntaDTO dadosPergunta) {
        Professor professor = professorRepository.findById(dadosPergunta.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dadosPergunta.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Pergunta pergunta = new Pergunta();
        pergunta.setEnunciado(dadosPergunta.getEnunciado());
        pergunta.setDisciplina(disciplina);
        pergunta.setCriadoPor(professor);

        List<Alternativa> alternativas = dadosPergunta.getAlternativas()
                .stream()
                .map(alternativaDTO -> new Alternativa(
                        null,
                        alternativaDTO.getTexto(),
                        alternativaDTO.isCorreta(),
                        pergunta
                ))
                .collect(Collectors.toList());

        pergunta.setAlternativasParaCadastro(alternativas);

        Pergunta perguntaSalva = perguntaRepository.save(pergunta);

        return new ListarPerguntaDTO(perguntaSalva);
    }


    public List<ListarPerguntaDTO> listarTodasAsPerguntas() {
        List<Pergunta> perguntas = perguntaRepository.findAll();

        return perguntas.stream()
                .map(pergunta -> new ListarPerguntaDTO(pergunta))
                .collect(Collectors.toList());
    }

    public ListarPerguntaDTO atualizarPergunta (Long id, EditarPerguntaDTO dadosPergunta) {

        Professor professorEditor = professorRepository.findById(dadosPergunta.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor (editor) não encontrado."));

        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada."));

        Disciplina disciplina = disciplinaRepository.findById(dadosPergunta.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));

        pergunta.setEnunciado(dadosPergunta.getEnunciado());
        pergunta.setDisciplina(disciplina);

        sincronizarAlternativas(pergunta, dadosPergunta.getAlternativas());


        Pergunta perguntaAtualizada = perguntaRepository.save(pergunta);

        return new ListarPerguntaDTO(perguntaAtualizada);
    }

    //Esse método será usado quando fizermos a parte do login
    /*private void validarPermissao (Professor professor, Disciplina disciplina) {

        if (professor.getPerfilProfessor().equals("COORDENADOR")) {
            return;
        }

        boolean lecionaEssaMateria = professor.getDisciplinas()
                .stream()
                .anyMatch(disciplinaDTO -> disciplinaDTO.getId().equals(disciplina.getId()));

        if (lecionaEssaMateria) {
            return;
        }

        throw new RuntimeException("Permissão negada. Você não é coordenador ou não leciona esta disciplina.");
    }*/

    private void sincronizarAlternativas(Pergunta pergunta, List<AlternativaCadastradaDTO> dtos) {

        Map<Long, Alternativa> mapaAntigas = pergunta.getAlternativas().stream()
                .collect(Collectors.toMap(Alternativa::getId, Function.identity()));

        List<Alternativa> listaViva = pergunta.getAlternativas();

        Set<Long> idsProcessados = new HashSet<>();

        for (AlternativaCadastradaDTO dto : dtos) {
            if (dto.getId() == null) {
                Alternativa nova = new Alternativa(null, dto.getTexto(), dto.isCorreta(), pergunta);
                listaViva.add(nova);
            } else {
                Alternativa existente = mapaAntigas.get(dto.getId());
                if (existente != null) {
                    existente.setTexto(dto.getTexto());
                    existente.setCorreta(dto.isCorreta());
                    idsProcessados.add(existente.getId());
                }
            }
        }

        List<Alternativa> paraRemover = new ArrayList<>();
        for (Alternativa antiga : mapaAntigas.values()) {
            if (!idsProcessados.contains(antiga.getId())) {
                paraRemover.add(antiga);
            }
        }

        listaViva.removeAll(paraRemover);
    }

/*
    public Pergunta buscarPorId(Long id) {

    }
*/
    public void excluirPergunta(Long id) {

        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada."));

        perguntaRepository.delete(pergunta);

    }
}

