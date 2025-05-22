package com.autobots.automanager.modelos.adicionadores;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.UsuarioControle;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.modelos.AdicionadorLink;

@Component
public class UsuarioAdicionadorLink implements AdicionadorLink<Usuario> {

  @Override
  public void adicionarLink(List<Usuario> lista) {
    for (Usuario usuario : lista) {
      long id = usuario.getId();
      Link linkProprio = WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder
              .methodOn(UsuarioControle.class)
              .obterUsuario(id))
          .withSelfRel();
      usuario.add(linkProprio);
    }
  }

  @Override
  public void adicionarLink(Usuario usuario) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(UsuarioControle.class)
            .obterUsuarios())
        .withRel("usuarios");
    usuario.add(linkProprio);
  }
}