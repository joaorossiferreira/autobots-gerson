package com.example.demo.controller;

import com.example.demo.entity.Loja;
import com.example.demo.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lojas")
public class LojaController {

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public List<Loja> listarTodas() {
        return lojaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loja> buscarPorId(@PathVariable Long id) {
        return lojaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Loja> salvar(@RequestBody Loja loja) {
        return ResponseEntity.ok(lojaRepository.save(loja));
    }

    @GetMapping("/marca/{marca}")
    public List<Loja> buscarPorMarca(@PathVariable String marca) {
        return lojaRepository.findByMarca(marca);
    }
}