package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lojas")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome; // Ex: "Toyota São Paulo", "Volkswagen Rio"
    private String marca; // "Toyota" ou "Volkswagen"
    private String endereco;
    private String telefone;
}