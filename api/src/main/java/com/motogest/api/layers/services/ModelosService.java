package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.entities.VariacoesModelos;
import com.motogest.api.layers.repositories.ModelosRepository;
import com.motogest.api.layers.repositories.MotosRepository;
import com.motogest.api.layers.repositories.VariacoesModeloRepository;

@Service
public class ModelosService {
    @Autowired
    ModelosRepository modelosRepository;

    @Autowired
    MarcasService marcasService;

    @Autowired
    VariacoesModeloRepository variacoesModeloRepository;

    @Autowired
    MotosRepository motosRepository;

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

        List<Motos> motos = motosRepository.findByModelo(modelo);

        if (!motos.isEmpty()) {
            throw new IllegalArgumentException("Não é possível deletar um modelo que possui motos associadas");
        }

        List<VariacoesModelos> variacoes = variacoesModeloRepository.findByModeloId(modelo.getId());

        if (!variacoes.isEmpty()) {
            throw new IllegalArgumentException("Não é possível deletar um modelo que possui variações associadas");
        }
        
        modelosRepository.delete(modelo);

        String feedback = String.format("Modelo '%s' deletado com sucesso!", modelo.getNome());
        return feedback;
    }
}
