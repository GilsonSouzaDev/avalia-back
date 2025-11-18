package com.fatec.avalia.service;

import com.fatec.avalia.dto.disciplina.AtualizarCadastroDisciplinaDTO;
import com.fatec.avalia.dto.disciplina.CadastrarDisciplinaDTO;
import com.fatec.avalia.dto.disciplina.ListarDisciplinaDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.repository.DisciplinaRepository;
import com.fatec.avalia.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Random;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;


    public DisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public ListarDisciplinaDTO cadastrarDisciplina(CadastrarDisciplinaDTO dados) {

        Boolean existeEssaDisciplina = disciplinaRepository.findByNomeIgnoreCase(dados.getNome()).isPresent();

        if (existeEssaDisciplina) {
            throw new IllegalArgumentException("Já existe uma disciplina com esse nome cadastrado no sistema");
        }

        if (dados.getCor() == null || dados.getCor().isEmpty()) {
            dados.setCor(gerarCor());
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dados.getNome());
        disciplina.setCor(dados.getCor().toLowerCase());

        disciplinaRepository.save(disciplina);
        return new ListarDisciplinaDTO(disciplina);
    }

    // Método responsável por gerar cor para as matérias cadastradas sem cores
    private String gerarCor() {
        Random numeroAleatorio = new Random();
        int r = numeroAleatorio.nextInt(256);
        int g = numeroAleatorio.nextInt(256);
        int b = numeroAleatorio.nextInt(256);
        return String.format("#%02x%02x%02x", r, g, b);
    }


    public ListarDisciplinaDTO buscarDisciplinaPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        return new ListarDisciplinaDTO(disciplina);
    }

    public List<ListarDisciplinaDTO> listarTodasAsDisciplinas() {
        return disciplinaRepository.findAll()
                .stream()
                .map(disciplina -> new ListarDisciplinaDTO(disciplina.getId(), disciplina.getNome(), disciplina.getCor()))
                .toList();
    }

    public List<ListarDisciplinaDTO> buscarDisciplinaPeloNome(String nome) {
        List<Disciplina> disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(nome);

        if (disciplinas.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma disciplina encontrada com esse nome");
        }

        return disciplinas.stream()
                .map(ListarDisciplinaDTO::new)
                .toList();
    }

    public ListarDisciplinaDTO atualizarDisciplina(Long id, AtualizarCadastroDisciplinaDTO dados) {
        Disciplina disciplina = disciplinaRepository.findById(dados.getId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        if (dados.getNome() != null) {
            disciplina.setNome(dados.getNome());
        }

        if (dados.getCor() != null) {
            disciplina.setCor(dados.getCor());
        }

        disciplinaRepository.save(disciplina);

        return new ListarDisciplinaDTO(disciplina);
    }

    public void excluirDisciplina(Long id) {

        Disciplina disciplina = disciplinaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Essa disciplina não foi encontrada"));

        disciplinaRepository.deleteById(id);
    }

}

