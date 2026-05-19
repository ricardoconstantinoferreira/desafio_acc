package com.accenture.desafio_acc.handler;

import com.accenture.desafio_acc.dto.ErroRespostaDto;
import com.accenture.desafio_acc.exception.DocumentoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DocumentoExisteExceptionHandler {

    @ExceptionHandler(DocumentoExisteException.class)
    public ResponseEntity<ErroRespostaDto> documentoExiste(DocumentoExisteException e) {
        ErroRespostaDto erroRespostaDto = new ErroRespostaDto(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroRespostaDto);
    }
}
