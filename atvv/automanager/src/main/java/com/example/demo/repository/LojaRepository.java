package com.example.demo.repository;

import com.example.demo.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
    List<Loja> findByMarca(String marca);
}