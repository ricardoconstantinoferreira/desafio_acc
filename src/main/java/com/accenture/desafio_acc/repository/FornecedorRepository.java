package com.accenture.desafio_acc.repository;

import com.accenture.desafio_acc.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
