package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.hateoas.HateoasResource;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<HateoasResource<Cliente>> listarTodos() {
        return clienteService.listarTodos().stream().map(cliente -> {
            HateoasResource<Cliente> resource = new HateoasResource<>(cliente);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(cliente.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientes/loja/{lojaId}")
                    .buildAndExpand(cliente.getLoja().getId()).toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HateoasResource<Cliente>> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> {
                    HateoasResource<Cliente> resource = new HateoasResource<>(cliente);
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                        "self"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientes").toUriString(),
                        "all"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientes/loja/{lojaId}")
                            .buildAndExpand(cliente.getLoja().getId()).toUriString(),
                        "loja"
                    );
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HateoasResource<Cliente>> salvar(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.salvar(cliente);
        HateoasResource<Cliente> resource = new HateoasResource<>(savedCliente);
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCliente.getId()).toUriString(),
            "self"
        );
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientes").toUriString(),
            "all"
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HateoasResource<Cliente>> atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        try {
            Cliente cliente = clienteService.atualizar(id, clienteAtualizado);
            HateoasResource<Cliente> resource = new HateoasResource<>(cliente);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientes").toUriString(),
                "all"
            );
            return ResponseEntity.ok(resource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loja/{lojaId}")
    public List<HateoasResource<Cliente>> listarPorLoja(@PathVariable Long lojaId) {
        return clienteService.listarPorLoja(lojaId).stream().map(cliente -> {
            HateoasResource<Cliente> resource = new HateoasResource<>(cliente);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientes/{id}")
                    .buildAndExpand(cliente.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }
}