package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.RepositorioVenda;

@RestController
@RequestMapping("/vendas")
public class ControleVenda {

    @Autowired
    private RepositorioVenda repositorio;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarVenda(@RequestBody Venda venda) {
        repositorio.save(venda);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('VENDEDOR')")
    @GetMapping("/minhas-vendas")
    public ResponseEntity<List<Venda>> listarMinhasVendas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Venda> vendas = repositorio.findByFuncionarioCredencialNomeUsuario(username);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/minhas-compras")
    public ResponseEntity<List<Venda>> listarMinhasCompras() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Venda> compras = repositorio.findByClienteCredencialNomeUsuario(username);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }
}