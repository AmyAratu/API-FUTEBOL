package com.exemplo.api.futebol.service;

import com.exemplo.api.futebol.model.Partida;
import com.exemplo.api.futebol.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    public Partida cadastrarPartida(Partida partida) throws Exception {
        validarPartida(partida);
        return partidaRepository.save(partida);
    }

    public Partida editarPartida(Long id, Partida partida) throws Exception {
        Optional<Partida> partidaExistente = partidaRepository.findById(id);
        if (partidaExistente.isEmpty()) {
            throw new Exception("Partida não encontrada.");
        }

        validarPartida(partida);
        partida.setId(id);
        return partidaRepository.save(partida);
    }

    public void removerPartida(Long id) throws Exception {
        Optional<Partida> partidaExistente = partidaRepository.findById(id);
        if (partidaExistente.isEmpty()) {
            throw new Exception("Partida não encontrada.");
        }

        partidaRepository.deleteById(id);
    }

    public Optional<Partida> buscarPartida(Long id) {
        return partidaRepository.findById(id);
    }

    public List<Partida> listarPartidas() {
        return partidaRepository.findAll();
    }

    private void validarPartida(Partida partida) throws Exception {
        if (partida.getClubeMandante().equals(partida.getClubeVisitante())) {
            throw new Exception("Clube mandante e visitante não podem ser iguais.");
        }
        if (partida.getDataHora().isBefore(partida.getClubeMandante().getDataCriacao()) ||
                partida.getDataHora().isBefore(partida.getClubeVisitante().getDataCriacao())) {
            throw new Exception("Data da partida não pode ser anterior à criação dos clubes.");
        }
        if (partida.getGolsMandante() < 0 || partida.getGolsVisitante() < 0) {
            throw new Exception("Número de gols não pode ser negativo.");
        }
        // Adicione mais validações conforme necessário
    }
}