package com.autobots.automanager.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HateoasAdicionador {
    
    public void adicionarLink(Object objeto, Class<?> controlador, String rel) {
        if (objeto instanceof List<?>) {
            ((List<?>) objeto).forEach(item -> adicionarLinkIndividual(item, controlador, rel));
        } else {
            adicionarLinkIndividual(objeto, controlador, rel);
        }
    }

    private void adicionarLinkIndividual(Object objeto, Class<?> controlador, String rel) {
        try {
            Long id = (Long) objeto.getClass().getMethod("getId").invoke(objeto);
            
            // Link para o próprio recurso
            Link selfLink = WebMvcLinkBuilder.linkTo(controlador)
                .slash(id)
                .withRel(rel);
            objeto.getClass().getMethod("add", Link.class).invoke(objeto, selfLink);
            
            // Link para listagem
            Link listarLink = WebMvcLinkBuilder.linkTo(controlador)
                .withRel("todos");
            objeto.getClass().getMethod("add", Link.class).invoke(objeto, listarLink);
            
        } catch (Exception e) {
            // Ignore se o método não existir
        }
    }
}