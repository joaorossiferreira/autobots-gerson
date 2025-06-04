package com.example.demo.repository;

import com.example.demo.entity.ServicoOuMercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServicoOuMercadoriaRepository extends JpaRepository<ServicoOuMercadoria, Long> {
    List<ServicoOuMercadoria> findByLojaId(Long lojaId);
    // Adicione métodos personalizados, se necessário
}
