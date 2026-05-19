package com.accenture.desafio_acc.services.impl;

import com.accenture.desafio_acc.dto.FornecedorDto;
import com.accenture.desafio_acc.entity.Fornecedor;
import com.accenture.desafio_acc.exception.DocumentoExisteException;
import com.accenture.desafio_acc.repository.EmpresaRepository;
import com.accenture.desafio_acc.repository.FornecedorRepository;
import com.accenture.desafio_acc.services.FornecedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final EmpresaRepository empresaRepository;

    public FornecedorServiceImpl(FornecedorRepository fornecedorRepository, EmpresaRepository empresaRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public FornecedorDto create(FornecedorDto fornecedorDto) {

        if (existsByDocumento(fornecedorDto.getDocumento())) {
            throw new DocumentoExisteException("Erro: Documento (CPF/CNPJ) já consta na nossa base de dados.");
        }

        Fornecedor fornecedor = toEntity(fornecedorDto);
        Fornecedor saved = fornecedorRepository.save(fornecedor);
        return toDto(saved);
    }

    @Override
    public List<FornecedorDto> findAll() {
        return fornecedorRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public FornecedorDto findById(Long id) {
        return fornecedorRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public FornecedorDto update(Long id, FornecedorDto fornecedorDto) {
        Optional<Fornecedor> opt = fornecedorRepository.findById(id);
        if (opt.isEmpty()) return null;
        Fornecedor fornecedor = opt.get();
        fornecedor.setDocumento(fornecedorDto.getDocumento());
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setCep(fornecedorDto.getCep());
        fornecedor.setRg(fornecedorDto.getRg());
        fornecedor.setNascimento(fornecedorDto.getNascimento());

        Fornecedor saved = fornecedorRepository.save(fornecedor);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    private FornecedorDto toDto(Fornecedor fornecedor) {
        FornecedorDto dto = new FornecedorDto();
        dto.setId(fornecedor.getId());
        dto.setDocumento(fornecedor.getDocumento());
        dto.setNome(fornecedor.getNome());
        dto.setEmail(fornecedor.getEmail());
        dto.setCep(fornecedor.getCep());
        dto.setRg(fornecedor.getRg());
        dto.setNascimento(fornecedor.getNascimento());

        return dto;
    }

    private Fornecedor toEntity(FornecedorDto dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setDocumento(dto.getDocumento());
        fornecedor.setNome(dto.getNome());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setCep(dto.getCep());
        fornecedor.setRg(dto.getRg());
        fornecedor.setNascimento(dto.getNascimento());
        // associate empresas if provided (will set the inverse side; for persistence we must update owning side)

        return fornecedor;
    }

    private boolean existsByDocumento(String documento) {
        return fornecedorRepository.existsByDocumento(documento);
    }
}
