package com.motogest.api.layers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.repositories.MarcasRepository;

@Service
public class MarcasService {
    @Autowired
    private MarcasRepository marcasRepository;

    public List<Marcas> listarMarcas() {
        return marcasRepository.findAll();
    }

    public Marcas buscarMarca(String nome) {
        return marcasRepository.findByNome(nome);
    }
}
