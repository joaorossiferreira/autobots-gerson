package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@SpringBootApplication
public class AplicacaoAutomanager {

    public static void main(String[] args) {
        SpringApplication.run(AplicacaoAutomanager.class, args);
    }

    @Component
    public static class Inicializador implements ApplicationRunner {
        @Autowired
        public ClienteRepositorio clienteRepo;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            Calendar calendario = Calendar.getInstance();
            calendario.set(2002, Calendar.JUNE, 15);

            Cliente cliente = new Cliente();
            cliente.setNome("Pedro Alcântara de Bragança e Bourbon");
            cliente.setDataCadastro(Calendar.getInstance().getTime());
            cliente.setDataNascimento(calendario.getTime());
            cliente.setNomeSocial("Dom Pedro");
            
            Telefone telefone = new Telefone();
            telefone.setDdd("21");
            telefone.setNumero("981234576");
            cliente.getTelefones().add(telefone);
            
            Endereco endereco = new Endereco();
            endereco.setEstado("Rio de Janeiro");
            endereco.setCidade("Rio de Janeiro");
            endereco.setBairro("Copacabana");
            endereco.setRua("Avenida Atlântica");
            endereco.setNumero("1702");
            endereco.setCodigoPostal("22021001");
            endereco.setInformacoesAdicionais("Hotel Copacabana palace");
            cliente.setEndereco(endereco);
            
            Documento rg = new Documento();
            rg.setTipo("RG");
            rg.setNumero("1500");
            
            Documento cpf = new Documento();
            cpf.setTipo("CPF");
            cpf.setNumero("00000000001");
            
            cliente.getDocumentos().add(rg);
            cliente.getDocumentos().add(cpf);
            
            clienteRepo.save(cliente);
        }
    }
}