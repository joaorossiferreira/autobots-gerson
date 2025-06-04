package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.RepositorioServico;

@RestController
@RequestMapping("/servicos")
public class ControleServico {

    @Autowired
    private RepositorioServico repositorio;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarServico(@RequestBody Servico servico) {
        repositorio.save(servico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/listar")
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = repositorio.findAll();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }
}