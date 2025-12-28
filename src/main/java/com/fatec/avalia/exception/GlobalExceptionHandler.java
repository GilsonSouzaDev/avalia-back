package com.fatec.avalia.exception;

import com.fatec.avalia.dto.error.MensagemErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura as exceções de negócio que lançamos manualmente (RuntimeException).
     * Ex: "A pergunta deve ter exatamente uma alternativa correta".
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MensagemErroDTO> handleRuntimeException(RuntimeException ex) {
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Captura erros de validação do Hibernate Validator (@NotBlank, @NotNull, etc).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErroDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        MensagemErroDTO erro = new MensagemErroDTO();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem("Erro de validação nos campos.");
        erro.setDetalhes(detalhes);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Captura erros genéricos inesperados.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErroDTO> handleGenericException(Exception ex) {
        MensagemErroDTO erro = new MensagemErroDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}