package com.example.demo.repository;

import com.example.demo.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByLojaId(Long lojaId);
    // Adicione métodos personalizados, se necessário
}
