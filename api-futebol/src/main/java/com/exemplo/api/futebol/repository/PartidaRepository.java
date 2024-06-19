package com.exemplo.api.futebol.repository;

import com.exemplo.api.futebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    // Adicione métodos de busca específicos se necessário
}
