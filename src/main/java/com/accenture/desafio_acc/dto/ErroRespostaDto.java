package com.accenture.desafio_acc.dto;

public class ErroRespostaDto {

    private int status;
    private String messagem;

    public ErroRespostaDto(int status, String messagem) {
        this.status = status;
        this.messagem = messagem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
