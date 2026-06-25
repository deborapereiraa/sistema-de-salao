package com.deborapereira.salaodebeleza.service;

import com.deborapereira.salaodebeleza.exception.ObjetoNaoEncontradoException;
import com.deborapereira.salaodebeleza.model.Funcionarios;
import com.deborapereira.salaodebeleza.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionariosService {
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Funcionarios cadastrar (Funcionarios funcionarios){
        return  funcionariosRepository.save(funcionarios);
    }

    public List<Funcionarios> buscarTodos (){
        return  funcionariosRepository.findAll();
    }

    public Funcionarios buscarId (Long id){
        return funcionariosRepository.findById(id)
                .orElseThrow(()->new ObjetoNaoEncontradoException("Funcionário de ID " + id + " não encontrado!"));
    }

    public void delete (Long id){
        funcionariosRepository.deleteById(id);
    }

    public Funcionarios alterarTelefone (Long id, String novoTelefone){
        Funcionarios funcionariosExistente = buscarId(id);
        funcionariosExistente.setNumeroTelefone(novoTelefone);
        return funcionariosRepository.save(funcionariosExistente);

    }

    public Funcionarios adicionarComissao(Long id, BigDecimal comissao) {
        Funcionarios funcionarioExistente = buscarId(id);
        funcionarioExistente.setComissao(comissao);
        return funcionariosRepository.save(funcionarioExistente);
    }

}
