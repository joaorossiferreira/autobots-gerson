package com.autobots.automanager.modelo;

import java.util.List;

import com.autobots.automanager.entidades.Telefone;

public class AtualizadorTelefone {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Telefone telefone, Telefone dadosAtualizacao) {
        if (dadosAtualizacao != null) {
            if (!verificador.verificar(dadosAtualizacao.getDdd())) {
                telefone.setDdd(dadosAtualizacao.getDdd());
            }
            if (!verificador.verificar(dadosAtualizacao.getNumero())) {
                telefone.setNumero(dadosAtualizacao.getNumero());
            }
        }
    }

    public void atualizar(List<Telefone> telefones, List<Telefone> dadosAtualizacao) {
        for (Telefone atualizacao : dadosAtualizacao) {
            for (Telefone telefone : telefones) {
                if (atualizacao.getId() != null && atualizacao.getId() == telefone.getId()) {
                    atualizar(telefone, atualizacao);
                }
            }
        }
    }
}