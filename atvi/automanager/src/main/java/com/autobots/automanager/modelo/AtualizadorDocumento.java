package com.autobots.automanager.modelo;

import java.util.List;

import com.autobots.automanager.entidades.Documento;

public class AtualizadorDocumento {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Documento documento, Documento dadosAtualizacao) {
        if (dadosAtualizacao != null) {
            if (!verificador.verificar(dadosAtualizacao.getTipo())) {
                documento.setTipo(dadosAtualizacao.getTipo());
            }
            if (!verificador.verificar(dadosAtualizacao.getNumero())) {
                documento.setNumero(dadosAtualizacao.getNumero());
            }
        }
    }

    public void atualizar(List<Documento> documentos, List<Documento> dadosAtualizacao) {
        for (Documento atualizacao : dadosAtualizacao) {
            for (Documento documento : documentos) {
                if (atualizacao.getId() != null && atualizacao.getId() == documento.getId()) {
                    atualizar(documento, atualizacao);
                }
            }
        }
    }
}