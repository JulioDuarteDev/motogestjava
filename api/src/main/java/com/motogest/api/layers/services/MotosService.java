package com.motogest.api.layers.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.entities.VariacoesModelos;
import com.motogest.api.layers.repositories.MotosRepository;

@Service
public class MotosService {
    @Autowired
    MotosRepository motosRepository;

    @Autowired
    ModelosService modelosService;

    @Autowired
    DisponibilidadeService disponibilidadeService;

    @Autowired
    VariacoesModeloService variacoesModeloService;


    String naoEncontrado = "Não foi possível encontrar uma moto com o parâmetro informado";

    public List<Motos> listarMotos() {
        List<Motos> motos = motosRepository.findAll();

        if(motos.isEmpty()) {
            throw new NoSuchElementException("Não existem motos cadastradas");
        }

        return motos;
    }

    public List<Motos> buscarMotosPorModelo(Integer modeloId) {
        Modelos modelo = modelosService.buscarModeloPorId(modeloId);

        List<Motos> motos = motosRepository.findByModelo(modelo);

        if (motos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return motos;
    }

    public List<Motos> buscarMotosPorDisponibilidade(Integer disponibilidadeId) {
        Disponibilidade disponibilidade = disponibilidadeService.buscarDisponibilidadePorId(disponibilidadeId);

        List<Motos> motos = motosRepository.findByDisponibilidade(disponibilidade);

        if (motos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return motos;
    }

    public List<Motos> buscarMotosPorVariacao(Integer variacaoId) {
        VariacoesModelos variacao = variacoesModeloService.buscarVariacaoPorId(variacaoId);

        List<Motos> motos = motosRepository.findByVariacao(variacao);

        if (motos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return motos;
    }

    public List<Motos> buscarMotosPorAno(Integer ano) {
        List<Motos> motos = motosRepository.findByAno(ano);

        if (motos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return motos;
    }

    public List<Motos> buscarMotosPorQuilometragem(Integer quilometragem) {
        List<Motos> motos = motosRepository.findByQuilometragem(quilometragem);

        if (motos.isEmpty()) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return motos;
    }

    public Motos buscarMotoPorPlaca(String placa) {
        Motos moto = motosRepository.findByPlaca(placa);

        if(moto == null) {
            throw new NoSuchElementException(naoEncontrado);
        }

        return moto;
    }

    public Motos buscarMotoPorId(Integer id) {
        return motosRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));
    }

    public String salvarMoto(Motos moto) {
        Motos motoExistente = motosRepository.findByPlaca(moto.getPlaca());

        if(motoExistente != null) {
            throw new IllegalArgumentException("Já existe uma moto com a placa informada");
        }

        motosRepository.save(moto);

        String feedback = String.format("Moto '%s' salva com sucesso!", moto.getPlaca());
        return feedback;
    }

    public String atualizarMoto(Motos moto) {
        motosRepository.findById(moto.getId()).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        motosRepository.save(moto);

        String feedback = String.format("Moto '%s' atualizada com sucesso!", moto.getPlaca());
        return feedback;
    }

    public String deletarMoto(Integer id) {
        Motos moto = motosRepository.findById(id).orElseThrow(() -> new NoSuchElementException(naoEncontrado));

        motosRepository.delete(moto);

        String feedback = String.format("Moto '%s' deletada com sucesso!", moto.getPlaca());
        return feedback;
    }
}
