package com.example.demo.service;

import com.example.demo.entity.ServicoOuMercadoria;
import com.example.demo.repository.ServicoOuMercadoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoOuMercadoriaService {

    @Autowired
    private ServicoOuMercadoriaRepository servicoOuMercadoriaRepository;

    public List<ServicoOuMercadoria> listarTodos() {
        return servicoOuMercadoriaRepository.findAll();
    }

    public Optional<ServicoOuMercadoria> buscarPorId(Long id) {
        return servicoOuMercadoriaRepository.findById(id);
    }

    public ServicoOuMercadoria salvar(ServicoOuMercadoria servicoOuMercadoria) {
        return servicoOuMercadoriaRepository.save(servicoOuMercadoria);
    }

    public ServicoOuMercadoria atualizar(Long id, ServicoOuMercadoria servicoAtualizado) {
        return servicoOuMercadoriaRepository.findById(id).map(servico -> {
            servico.setNome(servicoAtualizado.getNome());
            servico.setDescricao(servicoAtualizado.getDescricao());
            servico.setValor(servicoAtualizado.getValor());
            servico.setDataCadastro(servicoAtualizado.getDataCadastro());
            return servicoOuMercadoriaRepository.save(servico);
        }).orElseThrow(() -> new RuntimeException("Serviço ou Mercadoria não encontrado com o ID " + id));
    }

    public void deletar(Long id) {
        servicoOuMercadoriaRepository.deleteById(id);
    }

    public List<ServicoOuMercadoria> listarPorLoja(Long lojaId) {
        return servicoOuMercadoriaRepository.findByLojaId(lojaId);
    }
}
