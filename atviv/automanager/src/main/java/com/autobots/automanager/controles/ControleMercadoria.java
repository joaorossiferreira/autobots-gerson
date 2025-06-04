package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.repositorios.RepositorioMercadoria;

@RestController
@RequestMapping("/mercadorias")
public class ControleMercadoria {

    @Autowired
    private RepositorioMercadoria repositorio;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarMercadoria(@RequestBody Mercadoria mercadoria) {
        repositorio.save(mercadoria);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/listar")
    public ResponseEntity<List<Mercadoria>> listarMercadorias() {
        List<Mercadoria> mercadorias = repositorio.findAll();
        return new ResponseEntity<>(mercadorias, HttpStatus.OK);
    }
}