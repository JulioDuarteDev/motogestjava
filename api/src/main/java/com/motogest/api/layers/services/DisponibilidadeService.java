package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.repositories.DisponibilidadeRepository;
import com.motogest.api.layers.repositories.MotosRepository;

@Service
public class DisponibilidadeService {
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    MotosRepository motosRepository;

    String naoEncontrado = "Não foi possível encontrar uma disponibilidade com o parâmetro informado";

    public List<Disponibilidade> listarDisponibilidades() {
        List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();

        if(disponibilidades.isEmpty()) {
            throw new NoSuchElementException("Não existem disponibilidades cadastradas");
        }

        return disponibilidades;
    }

    public Disponibilidade buscarDisponibilidadePorNome(String nome) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findByNome(nome);

        if (disponibilidade == null) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return disponibilidade;
    }

    public Disponibilidade buscarDisponibilidadePorId(Integer id) {
        return disponibilidadeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));
    }

    public String salvarDisponibilidade(Disponibilidade disponibilidade) {
        Disponibilidade disponibilidadeExistente = disponibilidadeRepository.findByNome(disponibilidade.getNome());

        if(disponibilidadeExistente != null) {
            throw new IllegalArgumentException("Já existe uma disponibilidade com o nome informado");
        }

        disponibilidadeRepository.save(disponibilidade);

        String feedback = String.format("Disponibilidade '%s' salva com sucesso!", disponibilidade.getNome());
        return feedback;
    }

    public String atualizarDisponibilidade(Disponibilidade disponibilidade) {
        disponibilidadeRepository.findById(disponibilidade.getId()).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        disponibilidadeRepository.save(disponibilidade);

        String feedback = String.format("Disponibilidade '%s' atualizada com sucesso!", disponibilidade.getNome());
        return feedback;
    }

    public String deletarDisponibilidade(Integer id) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        List<Motos> motos = motosRepository.findByDisponibilidade(disponibilidade);

        if(!motos.isEmpty()) {
            throw new IllegalArgumentException("Não é possível deletar pois existem motos associadas a esta disponibilidade");
        }

        disponibilidadeRepository.delete(disponibilidade);

        String feedback = String.format("Disponibilidade '%s' deletada com sucesso!", disponibilidade.getNome());
        return feedback;
    }
}
