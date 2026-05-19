package com.accenture.desafio_acc.dto;

import java.util.HashSet;
import java.util.Set;

public class EmpresaDto {
    private Long id;
    private String documento;
    private String fantasia;
    private String cep;

    public EmpresaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
