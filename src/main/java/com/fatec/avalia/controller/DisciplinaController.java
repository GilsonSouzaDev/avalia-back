package com.fatec.avalia.controller;

import com.fatec.avalia.dto.DisciplinaDTO;
import com.fatec.avalia.entity.Disciplina;
import com.fatec.avalia.service.DisciplinaService;
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

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> listarTodos() {
        return ResponseEntity.ok(disciplinaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> buscarDisciplinaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> salvar(@RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.salvar(disciplinaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> atualizar(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.atualizar(id, disciplinaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        disciplinaService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}

