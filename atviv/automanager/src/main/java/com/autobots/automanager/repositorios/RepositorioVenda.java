package com.autobots.automanager.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.automanager.entidades.Venda;

public interface RepositorioVenda extends JpaRepository<Venda, Long> {
    List<Venda> findByFuncionarioCredencialNomeUsuario(String nomeUsuario);
    List<Venda> findByClienteCredencialNomeUsuario(String nomeUsuario);
}