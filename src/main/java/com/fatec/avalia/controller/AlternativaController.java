package com.fatec.avalia.controller;

import com.fatec.avalia.entity.Alternativa;
import com.fatec.avalia.service.AlternativaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alternativas")
public class AlternativaController {

    private final AlternativaService service;

    public AlternativaController(AlternativaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Alternativa> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Alternativa criar(@RequestBody Alternativa alternativa) {
        return service.salvar(alternativa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}

