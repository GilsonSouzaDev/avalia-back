package com.fatec.avalia.controller;

import com.fatec.avalia.dto.pergunta.PerguntaRequestDTO;
import com.fatec.avalia.dto.pergunta.PerguntaResponseDTO;
import com.fatec.avalia.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaController {

    private final PerguntaService service;

    public PerguntaController(PerguntaService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PerguntaResponseDTO> cadastrar(
            @RequestPart("pergunta") @Valid PerguntaRequestDTO dto,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        return ResponseEntity.ok(service.cadastrar(dto, imagem));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PerguntaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestPart("pergunta") @Valid PerguntaRequestDTO dto,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        return ResponseEntity.ok(service.atualizar(id, dto, imagem));
    }

    @GetMapping
    public ResponseEntity<List<PerguntaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<PerguntaResponseDTO>> listarPorProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(service.listarPorProfessor(professorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}