package com.motogest.api.layers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.entities.Modelos;

public interface ModelosRepository extends JpaRepository<Modelos, Integer>{
    Modelos findByNome(String nome);
    List<Modelos> findByMarca(Marcas marca);
    List<Modelos> findByCilindrada(Integer cilindrada);
}
