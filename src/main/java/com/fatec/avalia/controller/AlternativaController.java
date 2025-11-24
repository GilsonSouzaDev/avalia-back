package com.fatec.avalia.controller;

import com.fatec.avalia.dto.alternativa.AlternativaRequestDTO;
import com.fatec.avalia.dto.alternativa.AlternativaResponseDTO;
import com.fatec.avalia.service.AlternativaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alternativas")
public class AlternativaController {

    private final AlternativaService service;

    public AlternativaController(AlternativaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlternativaResponseDTO> salvar(@RequestBody @Valid AlternativaRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/pergunta/{perguntaId}")
    public ResponseEntity<List<AlternativaResponseDTO>> listarPorPergunta(@PathVariable Long perguntaId) {
        return ResponseEntity.ok(service.listarPorPergunta(perguntaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlternativaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AlternativaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}