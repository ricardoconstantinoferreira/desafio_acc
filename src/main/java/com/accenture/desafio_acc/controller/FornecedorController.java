package com.accenture.desafio_acc.controller;

import com.accenture.desafio_acc.dto.FornecedorDto;
import com.accenture.desafio_acc.services.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<FornecedorDto> create(@RequestBody FornecedorDto dto) {
        FornecedorDto created = fornecedorService.create(dto);
        return ResponseEntity.created(URI.create("/api/fornecedores/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorDto>> list() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDto> get(@PathVariable Long id) {
        FornecedorDto dto = fornecedorService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDto> update(@PathVariable Long id, @RequestBody FornecedorDto dto) {
        FornecedorDto updated = fornecedorService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
