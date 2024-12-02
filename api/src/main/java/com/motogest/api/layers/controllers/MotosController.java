package com.motogest.api.layers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motogest.api.layers.entities.Motos;
import com.motogest.api.layers.services.MotosService;

@RestController
@RequestMapping("/motos")
public class MotosController {

    @Autowired
    MotosService motosService;

    @GetMapping("/all")
    public List<Motos> getAllMotos() {
        return motosService.listarMotos();
    }

    @GetMapping("/modelo/{idModelo}")
    public List<Motos> getMotosByModelo(@PathVariable Integer idModelo) {
        return motosService.buscarMotosPorModelo(idModelo);
    }

    @GetMapping("/disponibilidade/{idDisponibilidade}")
    public List<Motos> getMotosByDisponibilidade(@PathVariable Integer idDisponibilidade) {
        return motosService.buscarMotosPorDisponibilidade(idDisponibilidade);
    }

    @GetMapping("/variacao/{idVariacao}")
    public List<Motos> getMotosByVariacao(@PathVariable Integer idVariacao) {
        return motosService.buscarMotosPorVariacao(idVariacao);
    }

    @GetMapping("/ano/{ano}")
    public List<Motos> getMotosByAno(@PathVariable Integer ano) {
        return motosService.buscarMotosPorAno(ano);
    }

    @GetMapping("/quilometragem/{quilometragem}")
    public List<Motos> getMotosByQuilometragem(@PathVariable Integer quilometragem) {
        return motosService.buscarMotosPorQuilometragem(quilometragem);
    }

    @GetMapping("/placa/{placa}")
    public Motos getMotoByPlaca(@PathVariable String placa) {
        return motosService.buscarMotoPorPlaca(placa);
    }

    @GetMapping("/{id}")
    public Motos getMotoById(@PathVariable Integer id) {
        return motosService.buscarMotoPorId(id);
    }

    @PostMapping("/")
    public String saveMoto(@RequestBody Motos moto) {
        return motosService.salvarMoto(moto);
    }

    @PutMapping("/")
    public String updateMoto(@RequestBody Motos moto) {
        return motosService.atualizarMoto(moto);
    }

    @DeleteMapping("/{id}")
    public String deleteMoto(@PathVariable Integer id) {
        return motosService.deletarMoto(id);
    }
    
}
