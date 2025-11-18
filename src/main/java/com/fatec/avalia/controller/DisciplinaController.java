package com.fatec.avalia.controller;

import com.fatec.avalia.dto.disciplina.AtualizarCadastroDisciplinaDTO;
import com.fatec.avalia.dto.disciplina.CadastrarDisciplinaDTO;
import com.fatec.avalia.dto.disciplina.ListarDisciplinaDTO;
import com.fatec.avalia.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<ListarDisciplinaDTO> cadastrarDisciplina(@RequestBody @Valid CadastrarDisciplinaDTO dados) {
        return ResponseEntity.ok(disciplinaService.cadastrarDisciplina(dados));
    }

    @GetMapping
    public ResponseEntity<List<ListarDisciplinaDTO>> listarTodasAsDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarTodasAsDisciplinas());
    }

    @GetMapping("/buscar")
    public List<ListarDisciplinaDTO> buscarDisciplinaPeloNome(@RequestParam String nome) {
        return disciplinaService.buscarDisciplinaPeloNome(nome);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ListarDisciplinaDTO> buscarDisciplinaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.buscarDisciplinaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListarDisciplinaDTO> atualizarDisciplina(@PathVariable Long id, @RequestBody AtualizarCadastroDisciplinaDTO dados) {
        return ResponseEntity.ok(disciplinaService.atualizarDisciplina(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        disciplinaService.excluirDisciplina(id);
        return ResponseEntity.noContent().build();
    }

}

