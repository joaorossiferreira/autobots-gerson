package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "servicos_mercadorias")
public class ServicoOuMercadoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
}
