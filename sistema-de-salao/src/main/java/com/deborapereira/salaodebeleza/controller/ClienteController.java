package com.deborapereira.salaodebeleza.controller;

import com.deborapereira.salaodebeleza.model.Cliente;
import com.deborapereira.salaodebeleza.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public Cliente adicionar (@Valid @RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @GetMapping
    public List<Cliente> buscarTodos (){
        return clienteService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId (@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

    @PatchMapping("/{id}")
    public Cliente atualizarTelefone(@PathVariable Long id, @Valid @RequestBody Cliente clienteComNovoTelefone) {
        return clienteService.atualizarTelefone(id, clienteComNovoTelefone.getTelefone());
    }
}