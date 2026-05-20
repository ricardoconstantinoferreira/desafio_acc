package com.accenture.desafio_acc.repository;

import com.accenture.desafio_acc.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    boolean existsByDocumento(String documento);
    boolean existsByNome(String nome);
    List<Fornecedor> findAllByEmpresasId(Long empresaId);
}
