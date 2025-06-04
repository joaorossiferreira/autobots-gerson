package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String documento; // CPF ou CNPJ

    private String telefone;

    private String endereco;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
}
