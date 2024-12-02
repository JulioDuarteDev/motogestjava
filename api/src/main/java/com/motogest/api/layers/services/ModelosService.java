package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.repositories.ModelosRepository;

@Service
public class ModelosService {
    @Autowired
    private ModelosRepository modelosRepository;

    @Autowired
    MarcasService marcasService;

    String naoEncontrado = "Não foi possível encontrar um modelo com o parâmetro informado";

    public List<Modelos> listarModelos() {
        List<Modelos> modelos = modelosRepository.findAll();

        if(modelos.isEmpty()) {
            throw new NoSuchElementException("Não existem modelos cadastrados");
        }
        
        return modelos;
    }

    public Modelos buscarModeloPorNome(String nome) {
        Modelos modelo = modelosRepository.findByNome(nome);

        if (modelo == null) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return modelo;
    }

    public Modelos buscarModeloPorId(Integer id) {
        return modelosRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));
    }

    public List<Modelos> buscarModelosPorMarca(Integer idMarca) {
        Marcas marca = marcasService.buscarMarcaPorId(idMarca);

        System.out.println(marca);
        
        List<Modelos> modelos = modelosRepository.findByMarca(marca);

        if (modelos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return modelos;
    }

    public List<Modelos> buscarModelosPorCilindrada(Integer cilindrada) {
        List<Modelos> modelos = modelosRepository.findByCilindrada(cilindrada);

        if (modelos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return modelos;
    }

    public String salvarModelo(Modelos modelo) {
        Modelos modeloExistente = modelosRepository.findByNome(modelo.getNome());

        if(modeloExistente != null) {
            throw new IllegalArgumentException("Já existe um modelo com o nome informado");
        }

        modelosRepository.save(modelo);

        String feedback = String.format("Modelo '%s' salvo com sucesso!", modelo.getNome());
        return feedback;
    }

    public String atualizarModelo(Modelos modelo) {
        modelosRepository.findById(modelo.getId()).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        modelosRepository.save(modelo);

        String feedback = String.format("Modelo '%s' atualizado com sucesso!", modelo.getNome());
        return feedback;
    }

    public String deletarModelo(Integer id) {
        Modelos modelo = modelosRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        modelosRepository.delete(modelo);

        String feedback = String.format("Modelo '%s' deletado com sucesso!", modelo.getNome());
        return feedback;
    }
}
