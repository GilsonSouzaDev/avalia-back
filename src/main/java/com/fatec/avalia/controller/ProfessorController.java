package com.fatec.avalia.controller;

import com.fatec.avalia.dto.login.EsqueciSenhaDTO;
import com.fatec.avalia.dto.login.LoginDTO;
import com.fatec.avalia.dto.professor.ProfessorRequestDTO;
import com.fatec.avalia.dto.professor.ProfessorResponseDTO;
import com.fatec.avalia.dto.professor.ProfessorUpdateDTO;
import com.fatec.avalia.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    // --- AUTENTICAÇÃO ---

    @PostMapping("/login")
    public ResponseEntity<ProfessorResponseDTO> login(@RequestBody @Valid LoginDTO loginData) {
        ProfessorResponseDTO professorLogado = service.autenticar(loginData.getEmail(), loginData.getSenha());
        return ResponseEntity.ok(professorLogado);
    }

    @PutMapping("/recuperar-senha")
    public ResponseEntity<Void> redefinirSenha(@RequestBody @Valid EsqueciSenhaDTO dados) {
        service.redefinirSenha(dados.getEmail(), dados.getNovaSenha());
        return ResponseEntity.noContent().build();
    }

    // --- CRUD ---

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> cadastrar(@RequestBody @Valid ProfessorRequestDTO dto) {
        ProfessorResponseDTO novoProfessor = service.salvar(dto);
        return ResponseEntity.ok(novoProfessor);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProfessorUpdateDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}