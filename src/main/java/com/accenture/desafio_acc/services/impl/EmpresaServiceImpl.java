package com.accenture.desafio_acc.services.impl;

import com.accenture.desafio_acc.dto.EmpresaDto;
import com.accenture.desafio_acc.entity.Empresa;
import com.accenture.desafio_acc.repository.EmpresaRepository;
import com.accenture.desafio_acc.repository.FornecedorRepository;
import com.accenture.desafio_acc.services.EmpresaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final FornecedorRepository fornecedorRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, FornecedorRepository fornecedorRepository) {
        this.empresaRepository = empresaRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public EmpresaDto create(EmpresaDto empresaDto) {
        Empresa empresa = toEntity(empresaDto);
        Empresa saved = empresaRepository.save(empresa);
        return toDto(saved);
    }

    @Override
    public List<EmpresaDto> findAll() {
        return empresaRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public EmpresaDto findById(Long id) {
        return empresaRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public EmpresaDto update(Long id, EmpresaDto empresaDto) {
        Optional<Empresa> opt = empresaRepository.findById(id);
        if (opt.isEmpty()) return null;
        Empresa empresa = opt.get();
        empresa.setDocumento(empresaDto.getDocumento());
        empresa.setFantasia(empresaDto.getFantasia());
        empresa.setCep(empresaDto.getCep());

        Empresa saved = empresaRepository.save(empresa);
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    private EmpresaDto toDto(Empresa empresa) {
        EmpresaDto dto = new EmpresaDto();
        dto.setId(empresa.getId());
        dto.setDocumento(empresa.getDocumento());
        dto.setFantasia(empresa.getFantasia());
        dto.setCep(empresa.getCep());
        return dto;
    }

    private Empresa toEntity(EmpresaDto dto) {
        Empresa empresa = new Empresa();
        empresa.setDocumento(dto.getDocumento());
        empresa.setFantasia(dto.getFantasia());
        empresa.setCep(dto.getCep());
        return empresa;
    }
}
