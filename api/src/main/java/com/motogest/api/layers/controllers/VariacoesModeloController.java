package com.motogest.api.layers.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.motogest.api.layers.entities.VariacoesModelos;
import com.motogest.api.layers.services.VariacoesModeloService;

@RestController
@RequestMapping("/variacoes-modelo")
public class VariacoesModeloController {
    @Autowired
    VariacoesModeloService variacoesModeloService;

    @GetMapping("/all")
    public List<VariacoesModelos> getAllVariacoes() {
        List<VariacoesModelos> variacoes = variacoesModeloService.listarVariacoes();

        if(variacoes.isEmpty()) {
            throw new NoSuchElementException("Não existem variações de modelo cadastradas");
        }

        return variacoes;
    }

    @GetMapping("/{id}")
    public VariacoesModelos getVariacaoById(@PathVariable Integer id) {
        return variacoesModeloService.buscarVariacaoPorId(id);
    }

    @GetMapping("/cor/{cor}")
    public List<VariacoesModelos> getVariacoesByCor(@PathVariable String cor) {
        return variacoesModeloService.buscarVariacoesPorCor(cor);
    }

    @GetMapping("/modelo/{idModelo}")
    public List<VariacoesModelos> getVariacoesByModeloId(@PathVariable Integer idModelo) {
        return variacoesModeloService.buscarVariacoesPorModeloId(idModelo);
    }

    @PostMapping("/")
    public String saveVariacao(@RequestBody VariacoesModelos variacao) {
        return variacoesModeloService.salvarVariacao(variacao);
    }

    @PutMapping("/")
    public String updateVariacao(@RequestBody VariacoesModelos variacao) {
        return variacoesModeloService.atualizarVariacao(variacao);
    }

    @DeleteMapping("/{id}")
    public String deleteVariacao(@PathVariable Integer id) {
        return variacoesModeloService.deletarVariacao(id);
    }
}
