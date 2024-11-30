package com.motogest.api.layers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.entities.VariacoesModelos;
import com.motogest.api.layers.repositories.MotosRepository;

public class MotosService {
    @Autowired
    private MotosRepository motosRepository;

    public List<Motos> listarMotos() {
        return motosRepository.findAll();
    }

    public List<Motos> buscarMotosPorModelo(Modelos modelo) {
        return motosRepository.findByModelo(modelo);
    }

    public List<Motos> buscarMotosPorDisponibilidade(Disponibilidade disponibilidade) {
        return motosRepository.findByDisponibilidade(disponibilidade);
    }

    public List<Motos> buscarMotosPorVariacao(VariacoesModelos variacao) {
        return motosRepository.findByVariacao(variacao);
    }

    public List<Motos> buscarMotosPorAno(Integer ano) {
        return motosRepository.findByAno(ano);
    }

    public List<Motos> buscarMotosPorQuilometragem(Integer quilometragem) {
        return motosRepository.findByQuilometragem(quilometragem);
    }

    public Motos buscarMotoPorPlaca(String placa) {
        return motosRepository.findByPlaca(placa);
    }
}
