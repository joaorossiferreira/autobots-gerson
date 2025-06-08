package com.example.demo.controller;

import com.example.demo.entity.Funcionario;
import com.example.demo.hateoas.HateoasResource;
import com.example.demo.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<HateoasResource<Funcionario>> listarTodos() {
        return funcionarioService.listarTodos().stream().map(funcionario -> {
            HateoasResource<Funcionario> resource = new HateoasResource<>(funcionario);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(funcionario.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/funcionarios/loja/{lojaId}")
                    .buildAndExpand(funcionario.getLoja().getId()).toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HateoasResource<Funcionario>> buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(funcionario -> {
                    HateoasResource<Funcionario> resource = new HateoasResource<>(funcionario);
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                        "self"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/funcionarios").toUriString(),
                        "all"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/funcionarios/loja/{lojaId}")
                            .buildAndExpand(funcionario.getLoja().getId()).toUriString(),
                        "loja"
                    );
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HateoasResource<Funcionario>> salvar(@RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.salvar(funcionario);
        HateoasResource<Funcionario> resource = new HateoasResource<>(savedFuncionario);
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedFuncionario.getId()).toUriString(),
            "self"
        );
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/funcionarios").toUriString(),
            "all"
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HateoasResource<Funcionario>> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        try {
            Funcionario funcionario = funcionarioService.atualizar(id, funcionarioAtualizado);
            HateoasResource<Funcionario> resource = new HateoasResource<>(funcionario);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/funcionarios").toUriString(),
                "all"
            );
            return ResponseEntity.ok(resource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loja/{lojaId}")
    public List<HateoasResource<Funcionario>> listarPorLoja(@PathVariable Long lojaId) {
        return funcionarioService.listarPorLoja(lojaId).stream().map(funcionario -> {
            HateoasResource<Funcionario> resource = new HateoasResource<>(funcionario);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/funcionarios/{id}")
                    .buildAndExpand(funcionario.getId()).toUriString(),
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