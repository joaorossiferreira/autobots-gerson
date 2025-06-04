package com.example.demo.service;

import com.example.demo.entity.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public List<Veiculo> listarPorCliente(Long clienteId) {
        return veiculoRepository.findByClienteId(clienteId);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id).map(veiculo -> {
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setAno(veiculoAtualizado.getAno());
            veiculo.setCor(veiculoAtualizado.getCor());
            veiculo.setCliente(veiculoAtualizado.getCliente());
            return veiculoRepository.save(veiculo);
        }).orElseThrow(() -> new RuntimeException("Veículo não encontrado com o ID " + id));
    }

    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }

    public List<Veiculo> listarPorLoja(Long lojaId) {
        return veiculoRepository.findByLojaId(lojaId);
    }

    public List<Veiculo> listarPorLojaECliente(Long lojaId, Long clienteId) {
        return veiculoRepository.findByLojaIdAndClienteId(lojaId, clienteId);
    }
}
