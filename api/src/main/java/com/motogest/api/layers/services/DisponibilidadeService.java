package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.repositories.DisponibilidadeRepository;

@Service
public class DisponibilidadeService {
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

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

        String feedback = "Disponibilidade " + disponibilidade.getNome() + " salva com sucesso";
        return feedback;
    }

    public String atualizarDisponibilidade(Disponibilidade disponibilidade) {
        disponibilidadeRepository.findById(disponibilidade.getId()).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        disponibilidadeRepository.save(disponibilidade);

        String feedback = "Disponibilidade " + disponibilidade.getNome() + " atualizada com sucesso";
        return feedback;
    }

    public String deletarDisponibilidade(Integer id) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        disponibilidadeRepository.delete(disponibilidade);

        String feedback = "Disponibilidade " + disponibilidade.getNome() + " deletada com sucesso";
        return feedback;
    }
}
