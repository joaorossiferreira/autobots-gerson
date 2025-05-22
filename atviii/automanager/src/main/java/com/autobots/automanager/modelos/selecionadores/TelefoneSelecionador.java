package com.autobots.automanager.modelos.selecionadores;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;

@Component
public class TelefoneSelecionador {
  public Telefone selecionar(List<Telefone> telefones, long id) {
    Telefone selecionado = null;
    for (Telefone telefone : telefones) {
      if (telefone.getId() == id) {
        selecionado = telefone;
      }
    }
    return selecionado;
  }

  public Telefone selecionar(Cliente cliente, long telefoneId) {
    return null;
  }
}