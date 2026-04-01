package br.com.api.controllers;

import br.com.api.dtos.PessoasRequestDTO;
import br.com.api.entidades.Animais;
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
    public ResponseEntity<List<Animais>> listar() {
        List<Animais> animaisList = animaisRepository.findAll();

        if (animaisList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(animaisList);

    }
    @PostMapping("/criar")
    public ResponseEntity<Animais> criar(
            @RequestBody PessoasRequestDTO pessoa) {
        Animais animaisPersist = new Animais();
        animaisPersist.setNome(Animais.getNome());
        animaisPersist.setRaca(Animais.getRaca());
        animaisPersist.setPeso(Animais.getRaca());

        Animais retorno = animaisRepository.save(animaisPersist);

        return ResponseEntity.status(201).body(retorno);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animais> atualizar(
            @RequestBody AnimaisRequestDTO animais,
            @PathVariable Long id
    ){


        if(animaisRepository.existsById(id)) {

            Animais animaisPersist = new Animais();
            animaisPersist.setNome(animais.getNome());
            animaisPersist.setRaca(animais.getSobrenome());
            animaisPersist.setId(id);

            Pessoas retorno = animaisRepository.save(animaisPersist);

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
