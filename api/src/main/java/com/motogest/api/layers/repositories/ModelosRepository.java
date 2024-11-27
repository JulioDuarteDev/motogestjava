package com.motogest.api.layers.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.entities.Modelos;

public interface ModelosRepository extends JpaRepository<Modelos, UUID>{
    Modelos findByNome(String nome);
    List<Modelos> findByMarca(Marcas marca);
    List<Modelos> findByCilindrada(Integer cilindrada);
}
