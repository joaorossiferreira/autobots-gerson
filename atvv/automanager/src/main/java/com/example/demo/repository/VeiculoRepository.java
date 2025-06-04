package com.example.demo.repository;

import com.example.demo.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    // Método para listar veículos por cliente
    List<Veiculo> findByClienteId(Long clienteId);
    List<Veiculo> findByLojaId(Long lojaId);
    List<Veiculo> findByLojaIdAndClienteId(Long lojaId, Long clienteId);
}
