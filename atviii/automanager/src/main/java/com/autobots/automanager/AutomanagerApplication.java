package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public ClienteRepositorio repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2002, 05, 15);

			Cliente cliente = new Cliente();
			cliente.setNome("João Vitor Rossi Ferreira");
			cliente.setDataCadastro(Calendar.getInstance().getTime());
			cliente.setDataNascimento(calendario.getTime());
			cliente.setNomeSocial("João Rossi");
			cliente.setCPF("111.222.333-44");
			cliente.setRG("12.345.678-9");

			Endereco endereco = new Endereco();
			endereco.setEstado("São Paulo");
			endereco.setCidade("São José dos Campos");
			endereco.setBairro("Jardim das Indústrias");
			endereco.setRua("Rua Pirassununga");
			endereco.setNumero("141");
			endereco.setCodigoPostal("12240160");
			endereco.setInformacoesAdicionais("Perto do Postinho");
			cliente.setEndereco(endereco);

			repositorio.save(cliente);

			Telefone telefone = new Telefone();
			telefone.setClienteId(cliente.getId());
			telefone.setDdd("21");
			telefone.setNumero("981234576");
			cliente.getTelefones().add(telefone);

			repositorio.save(cliente);

		}
	}
}