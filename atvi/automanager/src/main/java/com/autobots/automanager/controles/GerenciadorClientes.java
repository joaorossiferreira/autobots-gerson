package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.AtualizadorCliente;
import com.autobots.automanager.modelo.SelecionadorCliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/gerenciar/clientes")
public class GerenciadorClientes {
    @Autowired
    private ClienteRepositorio clienteRepo;
    @Autowired
    private SelecionadorCliente selecionador;

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable long id) {
        List<Cliente> clientes = clienteRepo.findAll();
        return selecionador.encontrar(clientes, id);
    }

    @GetMapping("/todos")
    public List<Cliente> listarTodosClientes() {
        return clienteRepo.findAll();
    }

    @PostMapping("/novo")
    public void cadastrarNovoCliente(@RequestBody Cliente novoCliente) {
        clienteRepo.save(novoCliente);
    }

    @PutMapping("/modificar")
    public void modificarCliente(@RequestBody Cliente dadosAtualizacao) {
        Cliente cliente = clienteRepo.getById(dadosAtualizacao.getId());
		AtualizadorCliente atualizador = new AtualizadorCliente();
        atualizador.atualizar(cliente, dadosAtualizacao);
        clienteRepo.save(cliente);
    }

    @DeleteMapping("/remover")
    public void removerCliente(@RequestBody Cliente clienteRemover) {
        Cliente cliente = clienteRepo.getById(clienteRemover.getId());
        clienteRepo.delete(cliente);
    }
}