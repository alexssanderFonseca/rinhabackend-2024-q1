package br.com.alexsdm.rinha_backend_2024_Q1.service

import br.com.alexsdm.rinha_backend_2024_Q1.dto.TransacaoCommand
import br.com.alexsdm.rinha_backend_2024_Q1.dto.TransacaoOutput
import br.com.alexsdm.rinha_backend_2024_Q1.exception.ClienteNaoEncontradoException
import br.com.alexsdm.rinha_backend_2024_Q1.exception.TransacaoInvalidaException
import br.com.alexsdm.rinha_backend_2024_Q1.model.Cliente
import br.com.alexsdm.rinha_backend_2024_Q1.model.Transacao
import br.com.alexsdm.rinha_backend_2024_Q1.repository.ClienteRepository
import br.com.alexsdm.rinha_backend_2024_Q1.repository.TransacaoRepository
import jakarta.transaction.Transactional
import org.hibernate.StaleObjectStateException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant


@Service
class TransacaoService(
    private val clienteRepository: ClienteRepository,
    private val transacaoRepository: TransacaoRepository
) {

    @Transactional
    fun executar(
        clienteId: Int,
        transacaoCommand: TransacaoCommand
    ): TransacaoOutput {
        val cliente = buscaCliente(clienteId)
        val transacao = gerarTransacao(transacaoCommand, cliente)
        atualizaSaldo(transacao, cliente)
        transacaoRepository.save(transacao)
        return TransacaoOutput(cliente.limite, cliente.saldo)
    }


    private fun buscaCliente(id: Int) =
        clienteRepository.buscaClienteParaAtualizacaoSaldo(id)
            .orElseThrow { ClienteNaoEncontradoException() }

    private fun gerarTransacao(transacaoCommand: TransacaoCommand, cliente: Cliente): Transacao {

        if (transacaoCommand.valor == null
            || transacaoCommand.tipo == null
            || transacaoCommand.descricao == null
        ) {
            throw TransacaoInvalidaException()
        }
        return Transacao(
            valor = transacaoCommand.valor.toInt(),
            tipo = transacaoCommand.tipo,
            descricao = transacaoCommand.descricao,
            realizadaEm = Instant.now(),
            cliente = cliente
        )
    }


    private fun atualizaSaldo(
        transacao: Transacao,
        cliente: Cliente
    ) {
        val tipoTransacao = transacao.tipo
        val valor = transacao.valor
        if (tipoTransacao.equals("C", ignoreCase = true)) {
            creditar(cliente, valor)
            return
        } else if (tipoTransacao.equals("D", ignoreCase = true)) {
            debitar(cliente, valor)
            return
        }
        throw TransacaoInvalidaException("Tipo da transacao incorreto")
    }

    private fun creditar(
        cliente: Cliente,
        credito: Int
    ) {
        cliente.creditar(credito)
    }

    private fun debitar(
        cliente: Cliente,
        debito: Int
    ) {
        cliente.debitar(debito)
    }


}