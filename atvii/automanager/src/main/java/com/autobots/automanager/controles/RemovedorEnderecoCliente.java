package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
public class RemovedorEnderecoCliente {

    @Autowired
    private ClienteRepositorio clienteRepo;
    @Autowired
    private EnderecoRepositorio enderecoRepo;
    
    @DeleteMapping("/endereco-cliente/remover")
    public void removerEnderecoCliente(@RequestBody Cliente dadosRemocao) {
        Cliente cliente = clienteRepo.getById(dadosRemocao.getId());
        enderecoRepo.delete(cliente.getEndereco());
        cliente.setEndereco(null);
        clienteRepo.save(cliente);
    }
}