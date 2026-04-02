package br.com.api.controllers;

import br.com.api.dtos.AnimaisRequestDTO;
import br.com.api.dtos.PessoasRequestDTO;
import br.com.api.entidades.Animal;
import br.com.api.entidades.Pessoas;
import br.com.api.repositorios.AnimaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animais")
public class AnimaisController {

    private AnimaisRepository animaisRepository;

    public AnimaisController (AnimaisRepository animaisRepository){
        this.animaisRepository = animaisRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Animal>> listar() {
        List<Animal> animaisList = animaisRepository.findAll();

        if (animaisList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(animaisList);

    }
    @PostMapping("/criar")
    public ResponseEntity<Animal> criar(
            @RequestBody AnimaisRequestDTO Animal) {
        Animal animaisPersist = new Animal();
        animaisPersist.setNome(Animal.getNome());
        animaisPersist.setRaca(Animal.getRaca());
        animaisPersist.setPeso(Animal.getPeso());

        Animal retorno = animaisRepository.save(animaisPersist);

        return ResponseEntity.status(201).body(retorno);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animal> atualizar(
            @RequestBody AnimaisRequestDTO animais,
            @PathVariable Long id
    ){


        if(animaisRepository.existsById(id)) {

            Animal animaisPersist = new Animal();
            animaisPersist.setNome(animais.getNome());
            animaisPersist.setRaca(animais.getRaca());
            animaisPersist.setId(id);

            Animal retorno = animaisRepository.save(animaisPersist);

            return ResponseEntity.ok(retorno);

        }

        return ResponseEntity.badRequest().body(null);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        if(animaisRepository.existsById(id)){
            animaisRepository.deleteById(id);

            return ResponseEntity.ok(null);

        }

        return ResponseEntity.badRequest().body(null);

    }



}
