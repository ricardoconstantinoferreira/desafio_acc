package com.accenture.desafio_acc.repository;

import com.accenture.desafio_acc.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    boolean existsByDocumento(String documento);
}
