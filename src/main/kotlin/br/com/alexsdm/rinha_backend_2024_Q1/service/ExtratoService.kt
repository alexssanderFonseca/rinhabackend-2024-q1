package br.com.alexsdm.rinha_backend_2024_Q1.service

import br.com.alexsdm.rinha_backend_2024_Q1.dto.ExtratoOutput
import br.com.alexsdm.rinha_backend_2024_Q1.dto.SaldoOutput
import br.com.alexsdm.rinha_backend_2024_Q1.dto.TransacaoExtratoOuput
import br.com.alexsdm.rinha_backend_2024_Q1.model.Cliente
import br.com.alexsdm.rinha_backend_2024_Q1.model.Transacao
import br.com.alexsdm.rinha_backend_2024_Q1.repository.ClienteRepository
import br.com.alexsdm.rinha_backend_2024_Q1.repository.TransacaoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@Service
class ExtratoService(
    private val transacaoRepository: TransacaoRepository,
    private val clienteRepository: ClienteRepository
) {

    fun executar(id: Int): ExtratoOutput {
        val cliente = buscaCliente(id)
        return gerarExtrato(cliente)
    }

    private fun buscaCliente(id: Int) =
        clienteRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    private fun gerarExtrato(cliente: Cliente): ExtratoOutput {
        val transacoes = transacaoRepository.findTop10ByClienteOrderByRealizadaEmDesc(cliente)
        return ExtratoOutput(
            SaldoOutput(cliente.saldo, Instant.now().toString(), cliente.limite),
            TransacaoExtratoOuput.toTransacoesOutput(transacoes)
        )
    }
}