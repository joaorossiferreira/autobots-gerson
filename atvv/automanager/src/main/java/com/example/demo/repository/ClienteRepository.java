package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByLojaId(Long lojaId);
    // Métodos personalizados podem ser adicionados aqui, por exemplo:
    // List<Cliente> findByNomeContaining(String nome);
}
