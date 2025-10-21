package com.fatec.avalia.controller;

import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.service.PerguntaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaController {

    private final PerguntaService service;

    public PerguntaController(PerguntaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pergunta> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Pergunta criar(@RequestBody Pergunta pergunta) {
        return service.salvar(pergunta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}

