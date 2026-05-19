package com.accenture.desafio_acc.exception;

public class DocumentoExisteException extends RuntimeException {
    public DocumentoExisteException (String mensagem) {
        super(mensagem);
    }
}
