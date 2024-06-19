package com.exemplo.api.futebol.service;

import com.exemplo.api.futebol.model.Clube;
import com.exemplo.api.futebol.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    public Clube cadastrarClube(Clube clube) throws Exception {
        validarClube(clube);
        Optional<Clube> clubeExistente = clubeRepository.findByNomeAndSiglaEstado(clube.getNome(), clube.getSiglaEstado());
        if (clubeExistente.isPresent()) {
            throw new Exception("Clube já existe no estado.");
        }
        return clubeRepository.save(clube);
    }

    public Clube editarClube(Long id, Clube clube) throws Exception {
        Optional<Clube> clubeExistente = clubeRepository.findById(id);
        if (clubeExistente.isEmpty()) {
            throw new Exception("Clube não encontrado.");
        }

        Clube clubeParaAtualizar = clubeExistente.get();
        if (!clubeParaAtualizar.getDataCriacao().isBefore(clube.getDataCriacao())) {
            throw new Exception("A data de criação não pode ser anterior à data original.");
        }

        validarClube(clube);
        clube.setId(id);
        return clubeRepository.save(clube);
    }

    public void inativarClube(Long id) throws Exception {
        Optional<Clube> clubeExistente = clubeRepository.findById(id);
        if (clubeExistente.isEmpty()) {
            throw new Exception("Clube não encontrado.");
        }

        Clube clubeParaInativar = clubeExistente.get();
        clubeParaInativar.setAtivo(false);
        clubeRepository.save(clubeParaInativar);
    }

    public Optional<Clube> buscarClube(Long id) {
        return clubeRepository.findById(id);
    }

    public List<Clube> listarClubes() {
        return clubeRepository.findAll();
    }

    private void validarClube(Clube clube) throws Exception {
        if (clube.getNome() == null || clube.getNome().length() < 2) {
            throw new Exception("Nome do clube inválido.");
        }
        if (!isEstadoValido(clube.getSiglaEstado())) {
            throw new Exception("Sigla do estado inválida.");
        }
        if (clube.getDataCriacao().isAfter(LocalDate.now().atStartOfDay())) {
            throw new Exception("Data de criação do clube não pode ser no futuro.");
        }
    }

    private boolean isEstadoValido(String estado) {
        // Adicione a lógica para verificar se o estado é válido
        return true; // Substitua esta linha com a lógica correta
    }
}