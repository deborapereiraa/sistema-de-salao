package com.deborapereira.salaodebeleza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dados_servicos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal preco;

    @NotBlank(message = "O nome do procedimento é obrigatório.")
    private String nomeProcedimento;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;



}
