package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.autobots.automanager.modelo.AdicionadorLinkCliente;

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
    @Autowired
    private AdicionadorLinkCliente adicionadorLink;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable long id) {
        List<Cliente> clientes = clienteRepo.findAll();
        Cliente cliente = selecionador.encontrar(clientes, id);
        
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        adicionadorLink.adicionarLink(cliente);
        adicionadorLink.adicionarLinkUpdate(cliente);
        adicionadorLink.adicionarLinkDelete(cliente);
        
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> listarTodosClientes() {
        List<Cliente> clientes = clienteRepo.findAll();
        
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        adicionadorLink.adicionarLink(clientes);
        for (Cliente cliente : clientes) {
            adicionadorLink.adicionarLinkUpdate(cliente);
            adicionadorLink.adicionarLinkDelete(cliente);
        }
        
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping("/novo")
    public ResponseEntity<?> cadastrarNovoCliente(@RequestBody Cliente novoCliente) {
        Cliente clienteSalvo = clienteRepo.save(novoCliente);
        
        adicionadorLink.adicionarLink(clienteSalvo);
        adicionadorLink.adicionarLinkUpdate(clienteSalvo);
        adicionadorLink.adicionarLinkDelete(clienteSalvo);
        
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarCliente(@RequestBody Cliente dadosAtualizacao) {
        Cliente cliente = clienteRepo.getById(dadosAtualizacao.getId());
        AtualizadorCliente atualizador = new AtualizadorCliente();
        atualizador.atualizar(cliente, dadosAtualizacao);
        clienteRepo.save(cliente);
        
        adicionadorLink.adicionarLink(cliente);
        adicionadorLink.adicionarLinkUpdate(cliente);
        adicionadorLink.adicionarLinkDelete(cliente);
        
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<?> removerCliente(@RequestBody Cliente clienteRemover) {
        Cliente cliente = clienteRepo.getById(clienteRemover.getId());
        clienteRepo.delete(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}