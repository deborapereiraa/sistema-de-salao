package com.deborapereira.salaodebeleza.service;

import com.deborapereira.salaodebeleza.exception.ObjetoNaoEncontradoException;
import com.deborapereira.salaodebeleza.model.Servico;
import com.deborapereira.salaodebeleza.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public Servico cadastroDosProcedimentos (Servico servico){
        return servicoRepository.save(servico);
    }

    public List<Servico> listarProcedimentos (){
        return servicoRepository.findAll();
    }

    public Servico listarIdProcedimentos (Long id){
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Procedimento "+ id +" não encontrado"));
    }

    public Servico alterarValorDoProcedimento (Long id, BigDecimal novoPreco){
        Servico buscarValor = listarIdProcedimentos(id);
        buscarValor.setPreco(novoPreco);
        return servicoRepository.save(buscarValor);
    }

}
