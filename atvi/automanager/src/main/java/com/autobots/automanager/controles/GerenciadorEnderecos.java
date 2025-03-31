package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.AtualizadorEndereco;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/gerenciar/enderecos")
public class GerenciadorEnderecos {
    
    @Autowired
    private EnderecoRepositorio enderecoRepo;
    
    @GetMapping("/listar")
    public List<Endereco> listarEnderecos() {
        return enderecoRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public Endereco buscarEndereco(@PathVariable Long id) {
        return enderecoRepo.findById(id).orElse(null);
    }
    
    @PutMapping("/alterar")
    public void alterarEndereco(@RequestBody Endereco dadosAtualizacao) {
        Endereco endereco = enderecoRepo.getById(dadosAtualizacao.getId());
		AtualizadorEndereco atualizador = new AtualizadorEndereco();
        atualizador.atualizar(endereco, dadosAtualizacao);
        enderecoRepo.save(endereco);
    }
}