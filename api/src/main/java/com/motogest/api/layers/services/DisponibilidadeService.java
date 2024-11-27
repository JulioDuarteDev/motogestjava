package com.motogest.api.layers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.repositories.DisponibilidadeRepository;

@Service
public class DisponibilidadeService {
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    public List<Disponibilidade> listarDisponibilidades() {
        return disponibilidadeRepository.findAll();
    }

    public Disponibilidade buscarDisponibilidade(String nome) {
        return disponibilidadeRepository.findByNome(nome);
    }
}
