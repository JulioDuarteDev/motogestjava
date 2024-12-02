package com.motogest.api.layers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.motogest.api.layers.entities.Disponibilidade;
import com.motogest.api.layers.services.DisponibilidadeService;

@RestController
@RequestMapping("/disponibilidade")
public class DisponibilidadeController {

    @Autowired
    DisponibilidadeService disponibilidadeService;

    @GetMapping("/all")
    public List<Disponibilidade> getAllDisponibilidades() {
        return disponibilidadeService.listarDisponibilidades();
    }

    @GetMapping("/nome/{nome}")
    public Disponibilidade getDisponibilidadeByNome(@PathVariable String nome) {
        return disponibilidadeService.buscarDisponibilidadePorNome(nome);
    }

    @GetMapping("/{id}")
    public Disponibilidade getDisponibilidadeById(@PathVariable Integer id) {
        return disponibilidadeService.buscarDisponibilidadePorId(id);
    }

    @PostMapping("/")
    public String saveDisponibilidade(@RequestBody Disponibilidade disponibilidade) {
        return disponibilidadeService.salvarDisponibilidade(disponibilidade);
    }

    @PutMapping("/")
    public String updateDisponibilidade(@RequestBody Disponibilidade disponibilidade) {
        return disponibilidadeService.atualizarDisponibilidade(disponibilidade);
    }

    @DeleteMapping("/{id}")
    public String deleteDisponibilidade(@PathVariable Integer id) {
        return disponibilidadeService.deletarDisponibilidade(id);
    }

}
