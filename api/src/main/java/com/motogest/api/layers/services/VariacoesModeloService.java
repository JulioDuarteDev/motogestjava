package com.motogest.api.layers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motogest.api.layers.repositories.VariacoesModeloRepository;

@Service
public class VariacoesModeloService {
    @Autowired
    private VariacoesModeloRepository variacoesModeloRepository;

}
