package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
public class ClienteDocumentoAtualizador {
    
    @Autowired
    private ClienteRepositorio clienteRepo;
    
    @PutMapping("/documento-cliente/atualizar")
    public void incluirDocumentoCliente(@RequestBody Cliente dadosAtualizacao) {
        Cliente cliente = clienteRepo.getById(dadosAtualizacao.getId());
        cliente.getDocumentos().addAll(dadosAtualizacao.getDocumentos());
        clienteRepo.save(cliente);
    }
}