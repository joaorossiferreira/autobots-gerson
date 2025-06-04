package com.example.demo.repository;

import com.example.demo.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByDataVendaBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Venda> findByLojaId(Long lojaId);
    List<Venda> findByLojaIdAndDataVendaBetween(Long lojaId, LocalDateTime inicio, LocalDateTime fim);
}
