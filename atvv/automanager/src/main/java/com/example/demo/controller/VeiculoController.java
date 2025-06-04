package com.example.demo.controller;

import com.example.demo.entity.Veiculo;
import com.example.demo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Veiculo> listarPorCliente(@PathVariable Long clienteId) {
        return veiculoService.listarPorCliente(clienteId);
    }

    @PostMapping
    public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoService.salvar(veiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        try {
            Veiculo veiculo = veiculoService.atualizar(id, veiculoAtualizado);
            return ResponseEntity.ok(veiculo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loja/{lojaId}")
    public List<Veiculo> listarPorLoja(@PathVariable Long lojaId) {
        return veiculoService.listarPorLoja(lojaId);
    }

    @GetMapping("/loja/{lojaId}/cliente/{clienteId}")
    public List<Veiculo> listarPorLojaECliente(
        @PathVariable Long lojaId, 
        @PathVariable Long clienteId) {
        return veiculoService.listarPorLojaECliente(lojaId, clienteId);
    }
}
