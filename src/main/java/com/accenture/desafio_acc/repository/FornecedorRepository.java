package com.accenture.desafio_acc.repository;

import com.accenture.desafio_acc.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    boolean existsByDocumento(String documento);
    Optional<Fornecedor> findByNome(String nome);
    Optional<Fornecedor> findByDocumento(String documento);
}
