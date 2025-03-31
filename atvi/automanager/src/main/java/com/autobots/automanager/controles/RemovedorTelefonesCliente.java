package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
public class RemovedorTelefonesCliente {
    @Autowired
    private ClienteRepositorio clienteRepo;
    @Autowired
    private TelefoneRepositorio telRepo;
    
    @DeleteMapping("/telefone-cliente/remover")
    public void removerTelefoneCliente(@RequestBody Cliente dadosRemocao) {
        Cliente cliente = clienteRepo.getById(dadosRemocao.getId());
        for (Telefone tel : dadosRemocao.getTelefones()) {
            Telefone telefone = telRepo.getById(tel.getId());
            cliente.getTelefones().remove(telefone);
        }
        clienteRepo.save(cliente);
    }
    
    @DeleteMapping("/telefones-cliente/limpar")
    public void limparTelefonesCliente(@RequestBody Cliente dadosRemocao) {
        Cliente cliente = clienteRepo.getById(dadosRemocao.getId());
        cliente.getTelefones().clear();
        clienteRepo.save(cliente);
    }
}