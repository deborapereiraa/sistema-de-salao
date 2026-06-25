package com.deborapereira.salaodebeleza.controller;


import com.deborapereira.salaodebeleza.model.Funcionarios;
import com.deborapereira.salaodebeleza.service.FuncionariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionariosService funcionariosService;

    @PostMapping
    public Funcionarios cadastrando (@Valid  @RequestBody Funcionarios funcionarios){
        return funcionariosService.cadastrar(funcionarios);
    }

    @GetMapping
    public List<Funcionarios> buscarFuncionarioPorLista(){
        return funcionariosService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Funcionarios buscarId (@PathVariable Long id){
        return funcionariosService.buscarId(id);
    }

    @DeleteMapping("/{id}")
    public void apagandoFuncionario (@PathVariable Long id){
        funcionariosService.delete(id);
    }

    @PatchMapping("/alterar-telefone/{id}")
    public Funcionarios alterarTelefoneFuncionario (@PathVariable Long id, @Valid @RequestParam String novoTelefone){
        return funcionariosService.alterarTelefone(id, novoTelefone);
    }

}
