package com.motogest.api.layers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Marcas;

public interface MarcasRepository extends JpaRepository<Marcas, Integer> {
    Marcas findByNome(String nome);
}
