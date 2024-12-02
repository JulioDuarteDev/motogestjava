package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.entities.VariacoesModelos;
import com.motogest.api.layers.repositories.MotosRepository;
import com.motogest.api.layers.repositories.VariacoesModeloRepository;

@Service
public class VariacoesModeloService {
    @Autowired
    private VariacoesModeloRepository variacoesModeloRepository;

    @Autowired
    MotosRepository motosRepository;

    String naoEncontrado = "Não foi possível encontrar uma variação de modelo com o parâmetro informado";

    public List<VariacoesModelos> listarVariacoes() {
        List<VariacoesModelos> variacoesModelo = variacoesModeloRepository.findAll();

        if(variacoesModelo.isEmpty()) {
            throw new NoSuchElementException("Não existem variações de modelo cadastradas");
        }

        return variacoesModelo;
    }

    public VariacoesModelos buscarVariacaoPorId(Integer id) {
        return variacoesModeloRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));
    }

    public List<VariacoesModelos> buscarVariacoesPorModeloId(Integer idModelo) {
        List<VariacoesModelos> variacoesModelo = variacoesModeloRepository.findByModeloId(idModelo);

        if (variacoesModelo.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return variacoesModelo;
    }

    public List<VariacoesModelos> buscarVariacoesPorCor(String cor) {
        List<VariacoesModelos> variacoesModelo = variacoesModeloRepository.findByCor(cor);

        if (variacoesModelo.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return variacoesModelo;
    }

    public String salvarVariacao(VariacoesModelos variacao) {
        variacoesModeloRepository.save(variacao);

        String feedback = "Variação de modelo salva com sucesso!";
        return feedback;
    }

    public String atualizarVariacao(VariacoesModelos variacao) {
        variacoesModeloRepository.findById(variacao.getId()).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        variacoesModeloRepository.save(variacao);

        String feedback = "Variação de modelo atualizada com sucesso!";
        return feedback;
    }

    public String deletarVariacao(Integer id) {
        VariacoesModelos variacao = variacoesModeloRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        List<Motos> motos = motosRepository.findByVariacao(variacao);

        if (!motos.isEmpty()) {
            throw new IllegalArgumentException("Não é possível deletar uma variação de modelo que possui motos associadas");
        }
        
        variacoesModeloRepository.deleteById(id);

        String feedback = "Variação de modelo deletada com sucesso!";
        return feedback;
    }
}
