package com.exemplo.api.futebol.controller;

import com.exemplo.api.futebol.model.Partida;
import com.exemplo.api.futebol.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @PostMapping
    public ResponseEntity<Partida> cadastrarPartida(@RequestBody Partida partida) {
        try {
            Partida novaPartida = partidaService.cadastrarPartida(partida);
            return new ResponseEntity<>(novaPartida, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> editarPartida(@PathVariable Long id, @RequestBody Partida partida) {
        try {
            Partida partidaAtualizada = partidaService.editarPartida(id, partida);
            return new ResponseEntity<>(partidaAtualizada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPartida(@PathVariable Long id) {
        try {
            partidaService.removerPartida(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPartida(@PathVariable Long id) {
        Optional<Partida> partida = partidaService.buscarPartida(id);
        return partida.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Partida>> listarPartidas() {
        List<Partida> partidas = partidaService.listarPartidas();
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }
}
