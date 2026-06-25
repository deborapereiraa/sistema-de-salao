package com.deborapereira.salaodebeleza.service;

import com.deborapereira.salaodebeleza.exception.ObjetoNaoEncontradoException;
import com.deborapereira.salaodebeleza.model.Cliente;
import com.deborapereira.salaodebeleza.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public Cliente salvar (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodos (){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId (Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente de ID " + id + " não encontrado!"));
    }

    public Cliente atualizarTelefone(Long id, String novoTelefone){
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow();
        clienteExistente.setTelefone(novoTelefone);
        return clienteRepository.save(clienteExistente);
    }

}
