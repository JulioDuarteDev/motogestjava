package com.motogest.api.layers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.repositories.ModelosRepository;

public class ModelosService {
    @Autowired
    private ModelosRepository modelosRepository;

    public List<Modelos> listarModelos() {
        return modelosRepository.findAll();
    }

    public Modelos buscarModelo(String nome) {
        return modelosRepository.findByNome(nome);
    }

    public List<Modelos> buscarModelosPorMarca(Marcas marca) {
        return modelosRepository.findByMarca(marca);
    }

    public List<Modelos> buscarModelosPorCilindrada(Integer cilindrada) {
        return modelosRepository.findByCilindrada(cilindrada);
    }
}
