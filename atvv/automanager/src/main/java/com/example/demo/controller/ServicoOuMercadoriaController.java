package com.example.demo.controller;

import com.example.demo.entity.ServicoOuMercadoria;
import com.example.demo.service.ServicoOuMercadoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoOuMercadoriaController {

    @Autowired
    private ServicoOuMercadoriaService servicoOuMercadoriaService;

    @GetMapping
    public List<ServicoOuMercadoria> listarTodos() {
        return servicoOuMercadoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoOuMercadoria> buscarPorId(@PathVariable Long id) {
        return servicoOuMercadoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServicoOuMercadoria> salvar(@RequestBody ServicoOuMercadoria servicoOuMercadoria) {
        return ResponseEntity.ok(servicoOuMercadoriaService.salvar(servicoOuMercadoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoOuMercadoria> atualizar(@PathVariable Long id, @RequestBody ServicoOuMercadoria servicoAtualizado) {
        try {
            ServicoOuMercadoria servico = servicoOuMercadoriaService.atualizar(id, servicoAtualizado);
            return ResponseEntity.ok(servico);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoOuMercadoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loja/{lojaId}")
    public List<ServicoOuMercadoria> listarPorLoja(@PathVariable Long lojaId) {
        return servicoOuMercadoriaService.listarPorLoja(lojaId);
    }
}
