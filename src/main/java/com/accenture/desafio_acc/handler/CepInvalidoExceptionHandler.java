package com.accenture.desafio_acc.handler;

import com.accenture.desafio_acc.dto.ErroRespostaDto;
import com.accenture.desafio_acc.exception.CepInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CepInvalidoExceptionHandler {

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ErroRespostaDto> cepInvalido(CepInvalidoException e) {
        ErroRespostaDto erroRespostaDto = new ErroRespostaDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroRespostaDto);
    }
}
