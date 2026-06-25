package com.deborapereira.salaodebeleza.controller;

import com.deborapereira.salaodebeleza.model.Atendimento;
import com.deborapereira.salaodebeleza.model.FormasDePagamento;
import com.deborapereira.salaodebeleza.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private  final AtendimentoService atendimentoService;

    @PostMapping
    public Atendimento agendandoSalao (@Valid @RequestBody Atendimento atendimento){
        return atendimentoService.agendarSalao(atendimento);
    }

    @GetMapping
    public List<Atendimento> agendados(){
        return atendimentoService.listarAgenda();
    }

    @PatchMapping("cancelando/{id}")
    public Atendimento cancelado (@PathVariable Long id){
        return atendimentoService.cancelar(id);
    }

    @PatchMapping("/reagendando/{id}")
    public Atendimento reagendado(@PathVariable Long id, @Valid  @RequestParam LocalDateTime novaData) {
        return atendimentoService.reagendamento(id, novaData);
    }

    @PatchMapping("finalizando/{id}")
    public Atendimento finalizando(@PathVariable Long id, @Valid @RequestParam FormasDePagamento formasDePagamento) {
        return atendimentoService.finalizado(id, formasDePagamento);
    }

    @PatchMapping ("/{id}/comissao")
    public ResponseEntity<Atendimento> finalizandoComComissao (@PathVariable Long id){
        Atendimento atendimentoFinalizado = atendimentoService.finalizarComissao(id);
        return ResponseEntity.ok(atendimentoFinalizado);
    }
}
