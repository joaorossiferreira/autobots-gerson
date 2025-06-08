package com.example.demo.controller;

import com.example.demo.entity.Veiculo;
import com.example.demo.hateoas.HateoasResource;
import com.example.demo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<HateoasResource<Veiculo>> listarTodos() {
        return veiculoService.listarTodos().stream().map(veiculo -> {
            HateoasResource<Veiculo> resource = new HateoasResource<>(veiculo);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(veiculo.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/loja/{lojaId}")
                    .buildAndExpand(veiculo.getLoja().getId()).toUriString(),
                "loja"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/cliente/{clienteId}")
                    .buildAndExpand(veiculo.getCliente().getId()).toUriString(),
                "cliente"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HateoasResource<Veiculo>> buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarPorId(id)
                .map(veiculo -> {
                    HateoasResource<Veiculo> resource = new HateoasResource<>(veiculo);
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                        "self"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos").toUriString(),
                        "all"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/loja/{lojaId}")
                            .buildAndExpand(veiculo.getLoja().getId()).toUriString(),
                        "loja"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/cliente/{clienteId}")
                            .buildAndExpand(veiculo.getCliente().getId()).toUriString(),
                        "cliente"
                    );
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<HateoasResource<Veiculo>> listarPorCliente(@PathVariable Long clienteId) {
        return veiculoService.listarPorCliente(clienteId).stream().map(veiculo -> {
            HateoasResource<Veiculo> resource = new HateoasResource<>(veiculo);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/{id}")
                    .buildAndExpand(veiculo.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "cliente"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<HateoasResource<Veiculo>> salvar(@RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoService.salvar(veiculo);
        HateoasResource<Veiculo> resource = new HateoasResource<>(savedVeiculo);
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedVeiculo.getId()).toUriString(),
            "self"
        );
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos").toUriString(),
            "all"
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HateoasResource<Veiculo>> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        try {
            Veiculo veiculo = veiculoService.atualizar(id, veiculoAtualizado);
            HateoasResource<Veiculo> resource = new HateoasResource<>(veiculo);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos").toUriString(),
                "all"
            );
            return ResponseEntity.ok(resource);
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
    public List<HateoasResource<Veiculo>> listarPorLoja(@PathVariable Long lojaId) {
        return veiculoService.listarPorLoja(lojaId).stream().map(veiculo -> {
            HateoasResource<Veiculo> resource = new HateoasResource<>(veiculo);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/{id}")
                    .buildAndExpand(veiculo.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/loja/{lojaId}/cliente/{clienteId}")
    public List<HateoasResource<Veiculo>> listarPorLojaECliente(
        @PathVariable Long lojaId, 
        @PathVariable Long clienteId) {
        return veiculoService.listarPorLojaECliente(lojaId, clienteId).stream().map(veiculo -> {
            HateoasResource<Veiculo> resource = new HateoasResource<>(veiculo);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/{id}")
                    .buildAndExpand(veiculo.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/loja/{lojaId}")
                    .buildAndExpand(lojaId).toUriString(),
                "loja"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/veiculos/cliente/{clienteId}")
                    .buildAndExpand(clienteId).toUriString(),
                "cliente"
            );
            return resource;
        }).collect(Collectors.toList());
    }
}