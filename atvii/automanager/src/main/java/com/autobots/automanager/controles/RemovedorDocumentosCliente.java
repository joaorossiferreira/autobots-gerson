package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
public class RemovedorDocumentosCliente {
    
    @Autowired
    private ClienteRepositorio clienteRepo;
    @Autowired
    private DocumentoRepositorio docRepo;
    
    @DeleteMapping("/documento-cliente/remover")
    public void removerDocumentoCliente(@RequestBody Cliente dadosRemocao) {
        Cliente cliente = clienteRepo.getById(dadosRemocao.getId());
        for (Documento doc : dadosRemocao.getDocumentos()) {
            Documento documento = docRepo.getById(doc.getId());
            cliente.getDocumentos().remove(documento);
        }
        clienteRepo.save(cliente);
    }
    
    @DeleteMapping("/documentos-cliente/limpar")
    public void limparDocumentosCliente(@RequestBody Cliente dadosRemocao) {
        Cliente cliente = clienteRepo.getById(dadosRemocao.getId());
        cliente.getDocumentos().clear();
        clienteRepo.save(cliente);
    }
}