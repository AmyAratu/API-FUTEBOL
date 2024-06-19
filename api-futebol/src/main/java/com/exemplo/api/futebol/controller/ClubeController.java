package com.exemplo.api.futebol.controller;

import com.exemplo.api.futebol.model.Clube;
import com.exemplo.api.futebol.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/clubes")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @PostMapping
    public ResponseEntity<Clube> cadastrarClube(@RequestBody Clube clube) {
        try {
            Clube novoClube = clubeService.cadastrarClube(clube);
            return new ResponseEntity<>(novoClube, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clube> editarClube(@PathVariable Long id, @RequestBody Clube clube) {
        try {
            Clube clubeAtualizado = clubeService.editarClube(id, clube);
            return new ResponseEntity<>(clubeAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarClube(@PathVariable Long id) {
        try {
            clubeService.inativarClube(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clube> buscarClube(@PathVariable Long id) {
        Optional<Clube> clube = clubeService.buscarClube(id);
        return clube.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Clube>> listarClubes() {
        List<Clube> clubes = clubeService.listarClubes();
        return new ResponseEntity<>(clubes, HttpStatus.OK);
    }
}