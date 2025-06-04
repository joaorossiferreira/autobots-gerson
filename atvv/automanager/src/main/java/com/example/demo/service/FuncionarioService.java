package com.example.demo.service;

import com.example.demo.entity.Funcionario;
import com.example.demo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setDocumento(funcionarioAtualizado.getDocumento());
            funcionario.setTelefone(funcionarioAtualizado.getTelefone());
            funcionario.setEndereco(funcionarioAtualizado.getEndereco());
            funcionario.setPerfil(funcionarioAtualizado.getPerfil());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID " + id));
    }

    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> listarPorLoja(Long lojaId) {
        return funcionarioRepository.findByLojaId(lojaId);
    }
}
