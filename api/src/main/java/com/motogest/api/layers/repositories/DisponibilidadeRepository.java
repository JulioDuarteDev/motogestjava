package com.motogest.api.layers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Disponibilidade;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, UUID> {
    Disponibilidade findByNome(String nome);
}
