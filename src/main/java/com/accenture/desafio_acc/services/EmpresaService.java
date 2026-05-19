package com.accenture.desafio_acc.services;

import com.accenture.desafio_acc.dto.EmpresaDto;

import java.util.List;
import java.util.Set;

public interface EmpresaService {
    EmpresaDto create(EmpresaDto empresaDto);
    List<EmpresaDto> findAll();
    EmpresaDto findById(Long id);
    EmpresaDto update(Long id, EmpresaDto empresaDto);
    void delete(Long id);
    boolean existsByDocumento(String documento);
    EmpresaDto addFornecedores(Long empresaId, Set<Long> fornecedorIds);
}
