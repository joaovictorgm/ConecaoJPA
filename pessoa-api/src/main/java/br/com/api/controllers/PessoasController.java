package br.com.api.controllers;

import br.com.api.dtos.PessoasRequestDTO;
import br.com.api.entidades.Pessoas;
import br.com.api.repositorios.PessoasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {

    private PessoasRepository pessoasRepository;

    public PessoasController(PessoasRepository pessoasRepository) {
        this.pessoasRepository = pessoasRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoas>> listar() {
        List<Pessoas> pessoasList = pessoasRepository.findAll();

        if (pessoasList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(pessoasList);

    }
 @PostMapping("/criar")
    public ResponseEntity<Pessoas> criar(
            @RequestBody PessoasRequestDTO pessoa) {
        Pessoas pessoasPersist = new Pessoas();
        pessoasPersist.setNome(pessoa.getNome());
        pessoasPersist.setSobrenome(pessoa.getSobrenome());

        Pessoas retorno = pessoasRepository.save(pessoasPersist);

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





