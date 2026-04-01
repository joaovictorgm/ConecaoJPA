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
    public ResponseEntity<Pessoas> criar(
            @RequestBody PessoasRequestDTO pessoa) {
        Animais animaisPersist = new Animais();
        animaisPersist.setNome(Animais.getNome());
        animaisPersist.setSobrenome(pessoa.getSobrenome());

        Pessoas retorno = animaisRepository.save(animaisPersist);

        return ResponseEntity.status(201).body(retorno);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoas> atualizar(
            @RequestBody PessoasRequestDTO pessoa,
            @PathVariable Long id
    ){


        if(pessoasRepository.existsById(id)) {

            Pessoas pessoaPersist = new Pessoas();
            pessoaPersist.setNome(pessoa.getNome());
            pessoaPersist.setSobrenome(pessoa.getSobrenome());
            pessoaPersist.setId(id);

            Pessoas retorno = pessoasRepository.save(pessoaPersist);

            return ResponseEntity.ok(retorno);

        }

        return ResponseEntity.badRequest().body(null);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        if(pessoasRepository.existsById(id)){
            pessoasRepository.deleteById(id);

            return ResponseEntity.ok(null);

        }

        return ResponseEntity.badRequest().body(null);

    }



}
