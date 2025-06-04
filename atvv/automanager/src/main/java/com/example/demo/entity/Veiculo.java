package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private String cor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Relacionamento com Cliente

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
}
