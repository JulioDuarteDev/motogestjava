package com.motogest.api.layers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.motogest.api.layers.entities.Modelos;
import com.motogest.api.layers.services.ModelosService;

@RestController
@RequestMapping("/modelos")
public class ModelosController {
    @Autowired
    ModelosService modelosService;

    @GetMapping("/all")
    public List<Modelos> getAllModelos() {
        return modelosService.listarModelos();
    }

    @GetMapping("/nome/{nome}")
    public Modelos getModeloByNome(@PathVariable String nome) {
        return modelosService.buscarModeloPorNome(nome);
    }

    @GetMapping("/marca/{idMarca}")
    public List<Modelos> getModelosByMarca(@PathVariable Integer idMarca) {
        return modelosService.buscarModelosPorMarca(idMarca);
    }

    @GetMapping("/cilindrada/{cilindrada}")
    public List<Modelos> getModelosByCilindrada(@PathVariable Integer cilindrada) {
        return modelosService.buscarModelosPorCilindrada(cilindrada);
    }

    @GetMapping("/{id}")
    public Modelos getModeloById(@PathVariable Integer id) {
        return modelosService.buscarModeloPorId(id);
    }

    @PostMapping("/")
    public String saveModelo(@RequestBody Modelos modelo) {
        return modelosService.salvarModelo(modelo);
    }

    @PutMapping("/")
    public String updateModelo(@RequestBody Modelos modelo) {
        return modelosService.atualizarModelo(modelo);
    }

    @DeleteMapping("/{id}")
    public String deleteModelo(@PathVariable Integer id) {
        return modelosService.deletarModelo(id);
    }
}
