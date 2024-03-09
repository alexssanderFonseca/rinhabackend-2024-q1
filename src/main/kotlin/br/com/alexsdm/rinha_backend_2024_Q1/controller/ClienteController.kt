package br.com.alexsdm.rinha_backend_2024_Q1.controller

import br.com.alexsdm.rinha_backend_2024_Q1.dto.ExtratoOutput
import br.com.alexsdm.rinha_backend_2024_Q1.dto.TransacaoCommand
import br.com.alexsdm.rinha_backend_2024_Q1.dto.TransacaoOutput
import br.com.alexsdm.rinha_backend_2024_Q1.service.ExtratoService
import br.com.alexsdm.rinha_backend_2024_Q1.service.TransacaoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("clientes")
class ClienteController(
    private val transacaoService: TransacaoService,
    private val extratoService: ExtratoService
) {


    @PostMapping("{id}/transacoes")
    fun transacionar(
        @RequestBody @Valid command: TransacaoCommand,
        @PathVariable id: Int
    ): ResponseEntity<TransacaoOutput> {
        val transacaoOutput = transacaoService.executar(id, command)
        return ResponseEntity.ok(transacaoOutput)


    }

    @GetMapping("/{id}/extrato")
    fun extrato(@PathVariable id: Int): ResponseEntity<ExtratoOutput> {
        val extrato = extratoService.executar(id)
        return ResponseEntity.ok(extrato);
    }


}