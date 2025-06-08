package com.example.demo.controller;

import com.example.demo.entity.Venda;
import com.example.demo.hateoas.HateoasResource;
import com.example.demo.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<HateoasResource<Venda>> listarTodas() {
        return vendaService.listarTodas().stream().map(venda -> {
            HateoasResource<Venda> resource = new HateoasResource<>(venda);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(venda.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas/loja/{lojaId}")
                    .buildAndExpand(venda.getLoja().getId()).toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HateoasResource<Venda>> buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id)
                .map(venda -> {
                    HateoasResource<Venda> resource = new HateoasResource<>(venda);
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                        "self"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas").toUriString(),
                        "all"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas/loja/{lojaId}")
                            .buildAndExpand(venda.getLoja().getId()).toUriString(),
                        "loja"
                    );
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HateoasResource<Venda>> salvar(@RequestBody Venda venda) {
        Venda savedVenda = vendaService.salvar(venda);
        HateoasResource<Venda> resource = new HateoasResource<>(savedVenda);
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedVenda.getId()).toUriString(),
            "self"
        );
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas").toUriString(),
            "all"
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HateoasResource<Venda>> atualizar(@PathVariable Long id, @RequestBody Venda vendaAtualizada) {
        try {
            Venda venda = vendaService.atualizar(id, vendaAtualizada);
            HateoasResource<Venda> resource = new HateoasResource<>(venda);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas").toUriString(),
                "all"
            );
            return ResponseEntity.ok(resource);
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
    public List<HateoasResource<Venda>> listarPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        return vendaService.listarPorPeriodo(dataInicio, dataFim).stream().map(venda -> {
            HateoasResource<Venda> resource = new HateoasResource<>(venda);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas/{id}")
                    .buildAndExpand(venda.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "periodo"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/loja/{lojaId}")
    public List<HateoasResource<Venda>> listarPorLoja(@PathVariable Long lojaId) {
        return vendaService.listarPorLoja(lojaId).stream().map(venda -> {
            HateoasResource<Venda> resource = new HateoasResource<>(venda);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas/{id}")
                    .buildAndExpand(venda.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/loja/{lojaId}/periodo")
    public List<HateoasResource<Venda>> listarPorLojaEPeriodo(
        @PathVariable Long lojaId,
        @RequestParam String inicio, 
        @RequestParam String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        return vendaService.listarPorLojaEPeriodo(lojaId, dataInicio, dataFim).stream().map(venda -> {
            HateoasResource<Venda> resource = new HateoasResource<>(venda);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas/{id}")
                    .buildAndExpand(venda.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/vendas/loja/{lojaId}")
                    .buildAndExpand(lojaId).toUriString(),
                "loja"
            );
            return resource;
        }).collect(Collectors.toList());
    }
}