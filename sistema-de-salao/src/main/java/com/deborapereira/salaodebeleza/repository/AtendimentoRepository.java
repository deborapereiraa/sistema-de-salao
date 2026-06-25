package com.deborapereira.salaodebeleza.repository;

import com.deborapereira.salaodebeleza.model.Atendimento;
import com.deborapereira.salaodebeleza.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface AtendimentoRepository extends JpaRepository <Atendimento, Long> {

    boolean existsByFuncionariosIdAndDataHorarioAndStatus (Long funcionarioId, LocalDateTime dataHorario, StatusAgendamento status);
}
