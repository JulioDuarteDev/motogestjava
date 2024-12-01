package com.motogest.api.layers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.entities.VariacoesModelos;

public interface MotosRepository extends JpaRepository<Motos, Integer> {
    List<Motos> findByModelo(Modelos modelo);
    List<Motos> findByDisponibilidade(Disponibilidade disponibilidade);
    List<Motos> findByVariacao(VariacoesModelos variacao);
    List<Motos> findByAno(Integer ano);
    List<Motos> findByQuilometragem(Integer quilometragem);
    Motos findByPlaca(String placa);
}
