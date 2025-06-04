package com.autobots.automanager.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Date cadastro;
    
    @ManyToOne
    private Usuario funcionario;
    
    @ManyToOne
    private Cliente cliente;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Servico> servicos;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Mercadoria> mercadorias;
}