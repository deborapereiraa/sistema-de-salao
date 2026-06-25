package com.deborapereira.salaodebeleza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "dados_agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataHorario;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FormasDePagamento formasDePagamento;

    @ManyToOne
    @JoinColumn(name = "servicos_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "funcionarios_id")
    private Funcionarios funcionarios;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
