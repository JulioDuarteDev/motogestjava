package com.motogest.api.layers.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.VariacoesModelos;

public interface VariacoesModeloRepository extends JpaRepository<VariacoesModelos, UUID> {
    
}
