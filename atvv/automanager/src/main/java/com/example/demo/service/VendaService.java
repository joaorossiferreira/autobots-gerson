package com.example.demo.service;

import com.example.demo.entity.Venda;
import com.example.demo.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public Venda salvar(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Venda atualizar(Long id, Venda vendaAtualizada) {
        return vendaRepository.findById(id).map(venda -> {
            venda.setCliente(vendaAtualizada.getCliente());
            venda.setFuncionario(vendaAtualizada.getFuncionario());
            venda.setServicoOuMercadoria(vendaAtualizada.getServicoOuMercadoria());
            venda.setValor(vendaAtualizada.getValor());
            venda.setDataVenda(vendaAtualizada.getDataVenda());
            return vendaRepository.save(venda);
        }).orElseThrow(() -> new RuntimeException("Venda não encontrada com o ID " + id));
    }

    public void deletar(Long id) {
        vendaRepository.deleteById(id);
    }

    public List<Venda> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByDataVendaBetween(inicio, fim);
    }

    public List<Venda> listarPorLoja(Long lojaId) {
        return vendaRepository.findByLojaId(lojaId);
    }

    public List<Venda> listarPorLojaEPeriodo(Long lojaId, LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByLojaIdAndDataVendaBetween(lojaId, inicio, fim);
    }
}
