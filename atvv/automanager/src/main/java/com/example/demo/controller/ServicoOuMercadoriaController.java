package com.example.demo.controller;

import com.example.demo.entity.ServicoOuMercadoria;
import com.example.demo.hateoas.HateoasResource;
import com.example.demo.service.ServicoOuMercadoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicos")
public class ServicoOuMercadoriaController {

    @Autowired
    private ServicoOuMercadoriaService servicoOuMercadoriaService;

    @GetMapping
    public List<HateoasResource<ServicoOuMercadoria>> listarTodos() {
        return servicoOuMercadoriaService.listarTodos().stream().map(servico -> {
            HateoasResource<ServicoOuMercadoria> resource = new HateoasResource<>(servico);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(servico.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicos/loja/{lojaId}")
                    .buildAndExpand(servico.getLoja().getId()).toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HateoasResource<ServicoOuMercadoria>> buscarPorId(@PathVariable Long id) {
        return servicoOuMercadoriaService.buscarPorId(id)
                .map(servico -> {
                    HateoasResource<ServicoOuMercadoria> resource = new HateoasResource<>(servico);
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                        "self"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicos").toUriString(),
                        "all"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicos/loja/{lojaId}")
                            .buildAndExpand(servico.getLoja().getId()).toUriString(),
                        "loja"
                    );
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HateoasResource<ServicoOuMercadoria>> salvar(@RequestBody ServicoOuMercadoria servicoOuMercadoria) {
        ServicoOuMercadoria savedServico = servicoOuMercadoriaService.salvar(servicoOuMercadoria);
        HateoasResource<ServicoOuMercadoria> resource = new HateoasResource<>(savedServico);
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedServico.getId()).toUriString(),
            "self"
        );
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicos").toUriString(),
            "all"
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HateoasResource<ServicoOuMercadoria>> atualizar(@PathVariable Long id, @RequestBody ServicoOuMercadoria servicoAtualizado) {
        try {
            ServicoOuMercadoria servico = servicoOuMercadoriaService.atualizar(id, servicoAtualizado);
            HateoasResource<ServicoOuMercadoria> resource = new HateoasResource<>(servico);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicos").toUriString(),
                "all"
            );
            return ResponseEntity.ok(resource);
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
    public List<HateoasResource<ServicoOuMercadoria>> listarPorLoja(@PathVariable Long lojaId) {
        return servicoOuMercadoriaService.listarPorLoja(lojaId).stream().map(servico -> {
            HateoasResource<ServicoOuMercadoria> resource = new HateoasResource<>(servico);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicos/{id}")
                    .buildAndExpand(servico.getId()).toUriString(),
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