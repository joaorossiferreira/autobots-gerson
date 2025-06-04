package com.example.demo.controller;

import com.example.demo.entity.Venda;
import com.example.demo.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> listarTodas() {
        return vendaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> salvar(@RequestBody Venda venda) {
        return ResponseEntity.ok(vendaService.salvar(venda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda vendaAtualizada) {
        try {
            Venda venda = vendaService.atualizar(id, vendaAtualizada);
            return ResponseEntity.ok(venda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/periodo")
    public List<Venda> listarPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        return vendaService.listarPorPeriodo(dataInicio, dataFim);
    }

    @GetMapping("/loja/{lojaId}")
    public List<Venda> listarPorLoja(@PathVariable Long lojaId) {
        return vendaService.listarPorLoja(lojaId);
    }

    @GetMapping("/loja/{lojaId}/periodo")
    public List<Venda> listarPorLojaEPeriodo(
        @PathVariable Long lojaId,
        @RequestParam String inicio, 
        @RequestParam String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        return vendaService.listarPorLojaEPeriodo(lojaId, dataInicio, dataFim);
    }
}
