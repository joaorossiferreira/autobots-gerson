package com.autobots.automanager.modelo;

import com.autobots.automanager.entidades.Endereco;

public class AtualizadorEndereco {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Endereco endereco, Endereco dadosAtualizacao) {
        if (dadosAtualizacao != null) {
            if (!verificador.verificar(dadosAtualizacao.getEstado())) {
                endereco.setEstado(dadosAtualizacao.getEstado());
            }
            if (!verificador.verificar(dadosAtualizacao.getCidade())) {
                endereco.setCidade(dadosAtualizacao.getCidade());
            }
            if (!verificador.verificar(dadosAtualizacao.getBairro())) {
                endereco.setBairro(dadosAtualizacao.getBairro());
            }
            if (!verificador.verificar(dadosAtualizacao.getRua())) {
                endereco.setRua(dadosAtualizacao.getRua());
            }
            if (!verificador.verificar(dadosAtualizacao.getNumero())) {
                endereco.setNumero(dadosAtualizacao.getNumero());
            }
            if (!verificador.verificar(dadosAtualizacao.getInformacoesAdicionais())) {
                endereco.setInformacoesAdicionais(dadosAtualizacao.getInformacoesAdicionais());
            }
        }
    }
}