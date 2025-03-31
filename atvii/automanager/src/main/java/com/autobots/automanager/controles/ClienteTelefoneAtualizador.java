package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
public class ClienteTelefoneAtualizador {
    
    @Autowired
    private ClienteRepositorio clienteRepo;
    
    @PutMapping("/telefone-cliente/adicionar")
    public void adicionarTelefoneCliente(@RequestBody Cliente dadosAtualizacao) {
        Cliente cliente = clienteRepo.getById(dadosAtualizacao.getId());
        cliente.getTelefones().addAll(dadosAtualizacao.getTelefones());
        clienteRepo.save(cliente);
    }
}