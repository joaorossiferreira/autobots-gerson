package com.autobots.automanager.modelo;

import com.autobots.automanager.entidades.Cliente;

public class AtualizadorCliente {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();
    private AtualizadorEndereco enderecoAtualizador = new AtualizadorEndereco();
	private AtualizadorDocumento documentoAtualizador = new AtualizadorDocumento();
	private AtualizadorTelefone telefoneAtualizador = new AtualizadorTelefone();

    private void atualizarDadosBasicos(Cliente cliente, Cliente dadosAtualizacao) {
        if (!verificador.verificar(dadosAtualizacao.getNome())) {
            cliente.setNome(dadosAtualizacao.getNome());
        }
        if (!verificador.verificar(dadosAtualizacao.getNomeSocial())) {
            cliente.setNomeSocial(dadosAtualizacao.getNomeSocial());
        }
        if (dadosAtualizacao.getDataCadastro() != null) {
            cliente.setDataCadastro(dadosAtualizacao.getDataCadastro());
        }
        if (dadosAtualizacao.getDataNascimento() != null) {
            cliente.setDataNascimento(dadosAtualizacao.getDataNascimento());
        }
    }

    public void atualizar(Cliente cliente, Cliente dadosAtualizacao) {
        atualizarDadosBasicos(cliente, dadosAtualizacao);
        enderecoAtualizador.atualizar(cliente.getEndereco(), dadosAtualizacao.getEndereco());
        documentoAtualizador.atualizar(cliente.getDocumentos(), dadosAtualizacao.getDocumentos());
        telefoneAtualizador.atualizar(cliente.getTelefones(), dadosAtualizacao.getTelefones());
    }
}