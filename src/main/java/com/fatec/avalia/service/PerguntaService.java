package com.fatec.avalia.service;

import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.repository.PerguntaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    private final PerguntaRepository repository;

    public PerguntaService(PerguntaRepository repository) {
        this.repository = repository;
    }

    public List<Pergunta> listarTodas() {
        return repository.findAll();
    }

    public Pergunta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }

    public Pergunta salvar(Pergunta pergunta) {
        return repository.save(pergunta);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

