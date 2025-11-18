package com.fatec.avalia.controller;

import com.fatec.avalia.dto.pergunta.CadastrarPerguntaDTO;
import com.fatec.avalia.dto.pergunta.EditarPerguntaDTO;
import com.fatec.avalia.dto.pergunta.ListarPerguntaDTO;
import com.fatec.avalia.entity.Pergunta;
import com.fatec.avalia.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaController {

    private final PerguntaService perguntaService;

    public PerguntaController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @PostMapping
    public ResponseEntity<ListarPerguntaDTO> cadastrarPergunta(@RequestBody @Valid CadastrarPerguntaDTO dadosPergunta) {

        return ResponseEntity.ok(perguntaService.cadastrarPergunta(dadosPergunta));
    }


    @GetMapping
    public ResponseEntity<List<ListarPerguntaDTO>> listarTodasAsPerguntas() {
        List<ListarPerguntaDTO> listaDePerguntas = perguntaService.listarTodasAsPerguntas();

        return ResponseEntity.ok(listaDePerguntas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListarPerguntaDTO> atualizarPergunta(@PathVariable Long id, @RequestBody @Valid EditarPerguntaDTO dadosPergunta) {
        ListarPerguntaDTO perguntaAtualizada = perguntaService.atualizarPergunta(id, dadosPergunta);

        return ResponseEntity.ok(perguntaAtualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPergunta(@PathVariable Long id) {
        perguntaService.excluirPergunta(id);

        return ResponseEntity.noContent().build();
    }
}

