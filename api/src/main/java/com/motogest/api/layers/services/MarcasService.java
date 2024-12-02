package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.repositories.MarcasRepository;
import com.motogest.api.layers.repositories.ModelosRepository;

@Service
public class MarcasService {
    @Autowired
    MarcasRepository marcasRepository;

    @Autowired
    ModelosRepository modelosRepository;

    String naoEncontrado = "Não foi possível encontrar uma marca com o parâmetro informado";

    public List<Marcas> listarMarcas() {
        List<Marcas> marcas = marcasRepository.findAll();

        if(marcas.isEmpty()) {
            throw new NoSuchElementException("Não existem marcas cadastradas");
        }

        return marcas;
    }

    public Marcas buscarMarcaPorNome(String nome) {
        Marcas marca = marcasRepository.findByNome(nome);

        if(marca == null) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return marca;
    }

    public Marcas buscarMarcaPorId(Integer id) {
        return marcasRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));
    }

    public String salvarMarca(Marcas marca) {
        Marcas marcaExistente = marcasRepository.findByNome(marca.getNome());

        if(marcaExistente != null) {
            throw new IllegalArgumentException("Já existe uma marca com o nome informado");
        }

        marcasRepository.save(marca);

        String feedback = String.format("Marca '%s' salva com sucesso!", marca.getNome());
        return feedback;
    }

    public String atualizarMarca(Marcas marca) {
        marcasRepository.findById(marca.getId()).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        marcasRepository.save(marca);

        String feedback = String.format("Marca '%s' atualizada com sucesso!", marca.getNome());
        return feedback;
    }

    public String deletarMarca(Integer id) {
        Marcas marca = marcasRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        List<Modelos> modelos = modelosRepository.findByMarca(marca);

        if(!modelos.isEmpty()) {
            throw new IllegalArgumentException("Não é possível deletar uma marca que possui modelos associados");
        }

        marcasRepository.delete(marca);

        String feedback = String.format("Marca '%s' deletada com sucesso!", marca.getNome());
        return feedback;
    }
}
