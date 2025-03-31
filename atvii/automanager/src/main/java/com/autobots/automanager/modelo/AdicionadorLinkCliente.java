package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.GerenciadorClientes;
import com.autobots.automanager.entidades.Cliente;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<Cliente> {

    @Override
    public void adicionarLink(List<Cliente> lista) {
        for (Cliente cliente : lista) {
            long id = cliente.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                    .methodOn(GerenciadorClientes.class)
                    .buscarClientePorId(id))
                .withRel("Visualizar cliente de id " + id);
            cliente.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Cliente objeto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(GerenciadorClientes.class)
                .listarTodosClientes())
            .withRel("Lista de clientes");
        objeto.add(linkProprio);
    }

    @Override
    public void adicionarLinkUpdate(Cliente objeto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(GerenciadorClientes.class)
                .modificarCliente(objeto))
            .withRel("Atualizar cliente de id " + objeto.getId());
        objeto.add(linkProprio);
    }

    @Override
    public void adicionarLinkDelete(Cliente objeto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(GerenciadorClientes.class)
                .removerCliente(objeto))
            .withRel("Excluir cliente de id " + objeto.getId());
        objeto.add(linkProprio);
    }
}