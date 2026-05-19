package com.accenture.desafio_acc.handler;

import com.accenture.desafio_acc.dto.ErroRespostaDto;
import com.accenture.desafio_acc.exception.NomeExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NomeExisteExceptionHandler {

    @ExceptionHandler(NomeExisteException.class)
    public ResponseEntity<ErroRespostaDto> nomeExiste(NomeExisteException e) {
        ErroRespostaDto erroRespostaDto = new ErroRespostaDto(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroRespostaDto);
    }
}
