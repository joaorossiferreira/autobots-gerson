package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.AtualizadorDocumento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/gerenciar/documentos")
public class GerenciadorDocumentos {
    
    @Autowired
    private DocumentoRepositorio docRepo;
    
    @GetMapping("/listar")
    public List<Documento> listarDocumentos() {
        return docRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public Documento buscarDocumento(@PathVariable Long id) {
        return docRepo.findById(id).orElse(null);
    }
    
    @PutMapping("/alterar")
    public void alterarDocumento(@RequestBody Documento dadosAtualizacao) {
        Documento documento = docRepo.getById(dadosAtualizacao.getId());
		AtualizadorDocumento atualizador = new AtualizadorDocumento();
        atualizador.atualizar(documento, dadosAtualizacao);
        docRepo.save(documento);
    }
}