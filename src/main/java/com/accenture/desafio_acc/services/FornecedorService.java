package com.accenture.desafio_acc.services;

import com.accenture.desafio_acc.dto.FornecedorDto;

import java.util.List;

public interface FornecedorService {
    FornecedorDto create(FornecedorDto fornecedorDto);
    List<FornecedorDto> findAll();
    FornecedorDto findById(Long id);
    FornecedorDto update(Long id, FornecedorDto fornecedorDto);
    void delete(Long id);
}
