package com.motogest.api.layers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Disponibilidade;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Integer> {
    Disponibilidade findByNome(String nome);
}
