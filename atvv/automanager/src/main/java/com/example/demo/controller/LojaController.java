package com.example.demo.controller;

import com.example.demo.entity.Loja;
import com.example.demo.hateoas.HateoasResource;
import com.example.demo.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lojas")
public class LojaController {

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public List<HateoasResource<Loja>> listarTodas() {
        return lojaRepository.findAll().stream().map(loja -> {
            HateoasResource<Loja> resource = new HateoasResource<>(loja);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(loja.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/lojas/marca/{marca}")
                    .buildAndExpand(loja.getMarca()).toUriString(),
                "marca"
            );
            return resource;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HateoasResource<Loja>> buscarPorId(@PathVariable Long id) {
        return lojaRepository.findById(id)
                .map(loja -> {
                    HateoasResource<Loja> resource = new HateoasResource<>(loja);
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                        "self"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/lojas").toUriString(),
                        "all"
                    );
                    resource.addLink(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/lojas/marca/{marca}")
                            .buildAndExpand(loja.getMarca()).toUriString(),
                        "marca"
                    );
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HateoasResource<Loja>> salvar(@RequestBody Loja loja) {
        Loja savedLoja = lojaRepository.save(loja);
        HateoasResource<Loja> resource = new HateoasResource<>(savedLoja);
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLoja.getId()).toUriString(),
            "self"
        );
        resource.addLink(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/lojas").toUriString(),
            "all"
        );
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/marca/{marca}")
    public List<HateoasResource<Loja>> buscarPorMarca(@PathVariable String marca) {
        return lojaRepository.findByMarca(marca).stream().map(loja -> {
            HateoasResource<Loja> resource = new HateoasResource<>(loja);
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/lojas/{id}")
                    .buildAndExpand(loja.getId()).toUriString(),
                "self"
            );
            resource.addLink(
                ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                "marca"
            );
            return resource;
        }).collect(Collectors.toList());
    }
}