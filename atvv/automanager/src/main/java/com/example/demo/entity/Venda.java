package com.example.demo.entity;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Funcionario;
import com.example.demo.entity.ServicoOuMercadoria;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "servico_ou_mercadoria_id")
    private ServicoOuMercadoria servicoOuMercadoria;

    private BigDecimal valor;

    private LocalDateTime dataVenda;
    
    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
}
