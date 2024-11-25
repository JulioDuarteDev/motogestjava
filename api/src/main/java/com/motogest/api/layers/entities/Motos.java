package com.motogest.api.layers.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
	private String placa;

    @Column(nullable = false)
    private Integer quilometragem;

    @ManyToOne
    @JoinColumn(name = "modelos_id")
    private Modelos modelo;

    @Column(nullable = false)
	private String observacoes;
    
    @ManyToOne
    @JoinColumn(name = "disponibilidade_id")
    private Disponibilidade disponibilidade;

    @ManyToOne
    @JoinColumn(name = "variacoes_modelos_id")
    private VariacoesModelos variacao;

    @Column(nullable = false)
    private Integer ano;
}
