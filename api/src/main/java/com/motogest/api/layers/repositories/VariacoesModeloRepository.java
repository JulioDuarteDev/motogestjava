package com.motogest.api.layers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.VariacoesModelos;

public interface VariacoesModeloRepository extends JpaRepository<VariacoesModelos, Integer> {
    List<VariacoesModelos> findByModeloId(Integer id);
    List<VariacoesModelos> findByCor(String cor);
}
