package com.deborapereira.salaodebeleza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dados_funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do funcionário é obrigatório.")
    private String nome;

    @NotBlank(message = "O telefone é obrigatório.")
    private String numeroTelefone;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    private BigDecimal comissao;

}
