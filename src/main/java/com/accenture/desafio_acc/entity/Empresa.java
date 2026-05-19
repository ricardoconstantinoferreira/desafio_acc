package com.accenture.desafio_acc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "fantasia", nullable = false)
    private String fantasia;

    @Column(name = "cep", nullable = false)
    private String cep;

    @ManyToMany
    @JoinTable(
            name = "empresa_fornecedor",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<Fornecedor> fornecedores = new HashSet<>();

    public Empresa() {
    }

    public Empresa(Long id, String documento, String fantasia, String cep) {
        this.id = id;
        this.documento = documento;
        this.fantasia = fantasia;
        this.cep = cep;
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

    public Set<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Set<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", fantasia='" + fantasia + '\'' +
                ", cep='" + cep + '\'' +
                ", fornecedoresCount=" + (fornecedores == null ? 0 : fornecedores.size()) +
                '}';
    }
}
