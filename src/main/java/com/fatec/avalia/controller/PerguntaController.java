package com.fatec.avalia.controller;

import com.fatec.avalia.dto.pergunta.PerguntaRequestDTO;
import com.fatec.avalia.dto.pergunta.PerguntaResponseDTO;
import com.fatec.avalia.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaController {

    private final PerguntaService service;

    public PerguntaController(PerguntaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PerguntaResponseDTO> cadastrar(@RequestBody @Valid PerguntaRequestDTO dto) {
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PerguntaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/professor/{codigoProfessor}")
    public ResponseEntity<List<PerguntaResponseDTO>> listarPorProfessor(@PathVariable Long codigoProfessor) {
        return ResponseEntity.ok(service.listarPorProfessor(codigoProfessor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerguntaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PerguntaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}