package com.fatec.avalia.service;

import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.repository.AlternativaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativaService {

    // Parei aqui
    private final AlternativaRepository alternativaRepository;

    public AlternativaService(AlternativaRepository alternativaRepository) {

        this.alternativaRepository = alternativaRepository;
    }

    public List<Alternativa> listarTodas() {

        return alternativaRepository.findAll();
    }

    public Alternativa salvar(Alternativa alternativa) {
        return alternativaRepository.save(alternativa);
    }

    public void excluir(Long id) {
        alternativaRepository.deleteById(id);
    }
}

