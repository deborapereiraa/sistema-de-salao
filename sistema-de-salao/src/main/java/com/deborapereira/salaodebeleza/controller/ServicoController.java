package com.deborapereira.salaodebeleza.controller;

import com.deborapereira.salaodebeleza.model.Servico;
import com.deborapereira.salaodebeleza.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;


    @PostMapping
    public Servico criandoServico (@Valid @RequestBody Servico servico){
        return servicoService.cadastroDosProcedimentos(servico);
    }

    @GetMapping
    public List<Servico> listandoTodosProcedimentos(){
        return servicoService.listarProcedimentos();
    }

    @GetMapping("/{id}")
    public Servico buscandoProcedimentosId (@PathVariable Long id){
        return servicoService.listarIdProcedimentos(id);
    }

    @PatchMapping("/{id}")
    public Servico atualizandoValor (@Valid @PathVariable Long id, @RequestBody Servico servico){
       return servicoService.alterarValorDoProcedimento(id, servico.getPreco());
    }


}

