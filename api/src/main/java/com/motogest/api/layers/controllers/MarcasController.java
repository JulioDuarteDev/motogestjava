package com.motogest.api.layers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.motogest.api.layers.entities.Marcas;
import com.motogest.api.layers.services.MarcasService;

@RestController
@RequestMapping("/marcas")
public class MarcasController {
    @Autowired
    MarcasService marcasService;

    @GetMapping("/all")
    public List<Marcas> getAllMarcas() {
        return marcasService.listarMarcas();
    }

    @GetMapping("/nome/{nome}")
    public Marcas getMarcaByNome(@PathVariable String nome) {
        return marcasService.buscarMarcaPorNome(nome);
    }

    @GetMapping("/{id}")
    public Marcas getMarcaById(@PathVariable Integer id) {
        return marcasService.buscarMarcaPorId(id);
    }

    @PostMapping("/")
    public String saveMarca(@RequestBody Marcas marca) {
        return marcasService.salvarMarca(marca);
    }

    @PutMapping("/")
    public String updateMarca(@RequestBody Marcas marca) {
        return marcasService.atualizarMarca(marca);
    }

    @DeleteMapping("/{id}")
    public String deleteMarca(@PathVariable Integer id) {
        return marcasService.deletarMarca(id);
    }
}
