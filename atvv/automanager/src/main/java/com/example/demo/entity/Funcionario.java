package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String documento; // CPF

    private String telefone;

    private String endereco;

    private String perfil; // Exemplo: Gerente, Vendedor

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
}
