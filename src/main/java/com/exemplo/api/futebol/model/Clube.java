package com.exemplo.api.futebol.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;

@Entity
@Table(name= "TB_Clubes")

public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String siglaEstado;

    @Column(nullable = false)
    private LocalDate dataCriacao;

    @Column(nullable = false)
    private boolean ativo;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public ChronoLocalDateTime<?> getDataCriacao() {
        return dataCriacao.atStartOfDay();
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

