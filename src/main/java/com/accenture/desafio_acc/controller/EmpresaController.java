package com.accenture.desafio_acc.controller;

import com.accenture.desafio_acc.dto.EmpresaDto;
import com.accenture.desafio_acc.dto.FornecedorDto;
import com.accenture.desafio_acc.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaDto> create(@RequestBody EmpresaDto dto) {
        EmpresaDto created = empresaService.create(dto);
        return ResponseEntity.created(URI.create("/api/empresas/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> list() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> get(@PathVariable Long id) {
        EmpresaDto dto = empresaService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> update(@PathVariable Long id, @RequestBody EmpresaDto dto) {
        EmpresaDto updated = empresaService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/fornecedores")
    public ResponseEntity<EmpresaDto> addFornecedores(@PathVariable("id") Long empresaId, @RequestBody java.util.Set<Long> fornecedorIds) {
        EmpresaDto updated = empresaService.addFornecedores(empresaId, fornecedorIds);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/fornecedores")
    public ResponseEntity<List<FornecedorDto>> getFornecedoresByEmpresa(@PathVariable("id") Long empresaId) {
        List<FornecedorDto> fornecedores = empresaService.findFornecedoresByEmpresaId(empresaId);
        return ResponseEntity.ok(fornecedores);
    }
}
