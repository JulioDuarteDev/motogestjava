package com.motogest.api.layers.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
