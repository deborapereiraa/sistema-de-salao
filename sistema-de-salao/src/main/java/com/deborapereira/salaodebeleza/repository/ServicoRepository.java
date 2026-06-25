package com.deborapereira.salaodebeleza.repository;

import com.deborapereira.salaodebeleza.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
