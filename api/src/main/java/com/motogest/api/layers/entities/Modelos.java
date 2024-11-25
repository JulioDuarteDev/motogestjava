package com.motogest.api.layers.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Modelos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
	private String nome;

    @ManyToOne
    @JoinColumn(name = "marcas_id")
    private Marcas marca;

    @Column(nullable = false)
    private Integer cilindrada;
}
