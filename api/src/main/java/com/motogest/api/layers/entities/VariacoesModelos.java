package com.motogest.api.layers.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VariacoesModelos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
	private String cor;

    @Column(nullable = false)
	private String url;

    @ManyToOne
    @JoinColumn(name = "modelos_id")
    private Modelos modelo;
}
