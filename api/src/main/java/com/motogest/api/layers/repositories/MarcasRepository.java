package com.motogest.api.layers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Marcas;

public interface MarcasRepository extends JpaRepository<Marcas, UUID> {
    Marcas findByNome(String nome);
}
