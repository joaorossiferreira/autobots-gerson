package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.AtualizadorTelefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/gerenciar/telefones")
public class GerenciadorTelefones {
    @Autowired
    private TelefoneRepositorio telRepo;
    
    @GetMapping("/listar")
    public List<Telefone> listarTelefones() {
        return telRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public Telefone buscarTelefone(@PathVariable Long id) {
        return telRepo.findById(id).orElse(null);
    }
    
    @PutMapping("/alterar")
    public void alterarTelefone(@RequestBody Telefone dadosAtualizacao) {
        Telefone telefone = telRepo.getById(dadosAtualizacao.getId());
		AtualizadorTelefone atualizador = new AtualizadorTelefone();
        atualizador.atualizar(telefone, dadosAtualizacao);
        telRepo.save(telefone);
    }
}