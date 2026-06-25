package com.deborapereira.salaodebeleza.service;

import com.deborapereira.salaodebeleza.exception.ObjetoNaoEncontradoException;
import com.deborapereira.salaodebeleza.exception.RegraNegocioException;
import com.deborapereira.salaodebeleza.mensagem.Client;
import com.deborapereira.salaodebeleza.model.Atendimento;
import com.deborapereira.salaodebeleza.model.FormasDePagamento;
import com.deborapereira.salaodebeleza.model.Funcionarios;
import com.deborapereira.salaodebeleza.model.StatusAgendamento;
import com.deborapereira.salaodebeleza.repository.AtendimentoRepository;
import com.deborapereira.salaodebeleza.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private Client client;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Atendimento agendarSalao(Atendimento atendimento) {
        boolean funcionarioOcupado = atendimentoRepository.existsByFuncionariosIdAndDataHorarioAndStatus(
                atendimento.getFuncionarios().getId(),
                atendimento.getDataHorario(),
                StatusAgendamento.AGENDADO
        );

        if (funcionarioOcupado) {
            throw new RegraNegocioException("Este funcionário já possui um agendamento para este dia e horário!");
        }

        atendimento.setStatus(StatusAgendamento.AGENDADO);
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);

        String texto = "Olá, " + atendimentoSalvo.getCliente().getNome()
                + "! Seu horário para o serviço de " + atendimentoSalvo.getServico().getNomeProcedimento()
                + " foi confirmado para o dia " + atendimentoSalvo.getDataHorario()
                + ". Esperamos você!";

        client.enviarMensagem(String.valueOf(atendimentoSalvo.getCliente().getTelefone()), texto);
        return atendimentoSalvo;
    }

    public Atendimento cancelar(Long id) {
        Atendimento atendimento = atendimentoRepository.findById(id).orElseThrow();
        atendimento.setStatus(StatusAgendamento.CANCELADO);
        return atendimentoRepository.save(atendimento);

    }

    public Atendimento finalizado(Long id, FormasDePagamento formasDePagamento) {
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Agendamento de ID " + id + " não existe no sistema!"));
        atendimento.setStatus(StatusAgendamento.REALIZADO);
        atendimento.setFormasDePagamento(formasDePagamento);
        return atendimentoRepository.save(atendimento);
    }


    public Atendimento reagendamento(Long id, LocalDateTime novaData) {
        Atendimento atendimento = atendimentoRepository.findById(id).orElseThrow();
        atendimento.setDataHorario(novaData);
        atendimento.setStatus(StatusAgendamento.AGENDADO);
        return atendimentoRepository.save(atendimento);

    }

    public List<Atendimento> listarAgenda() {
        return atendimentoRepository.findAll();
    }


    public Atendimento finalizarComissao (Long pagamentoId){
        Atendimento atendimento = atendimentoRepository.findById(pagamentoId).orElseThrow(
                ()->new RuntimeException("Agendamento não encontrado"));
        atendimento.setStatus(StatusAgendamento.REALIZADO);
        Funcionarios funcionario = atendimento.getFuncionarios();
        BigDecimal valorServico = atendimento.getServico().getPreco();
        BigDecimal valorComissao = valorServico.multiply(new BigDecimal("0.10"));
        BigDecimal comissaoAtual = funcionario.getComissao() != null ? funcionario.getComissao() : BigDecimal.ZERO;
        funcionario.setComissao(comissaoAtual.add(valorComissao));
        funcionariosRepository.save(funcionario);
        return atendimentoRepository.save(atendimento);

    }
}


