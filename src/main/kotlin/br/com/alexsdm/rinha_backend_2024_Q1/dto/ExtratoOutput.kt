package br.com.alexsdm.rinha_backend_2024_Q1.dto

import br.com.alexsdm.rinha_backend_2024_Q1.model.Transacao
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class ExtratoOutput(
    val saldo: SaldoOutput,
    @JsonProperty("ultimas_transacoes")
    val transacoes: List<TransacaoExtratoOuput>

)

data class SaldoOutput(
    val total: Int,
    val data_extrato: String,
    val limite: Int
)

data class TransacaoExtratoOuput(
    val valor: Int,
    val tipo: String,
    val descricao: String,
    @JsonProperty("realizada_em")
    val realizadaEm: String
) {
    companion object {

        fun toTransacoesOutput(transacoes: List<Transacao>): List<TransacaoExtratoOuput> {
            return transacoes.map { toTransacaoOutput(it) }
                .toList()
        }

        private fun toTransacaoOutput(transacao: Transacao): TransacaoExtratoOuput {
            return TransacaoExtratoOuput(
                valor = transacao.valor,
                tipo = transacao.tipo,
                descricao = transacao.descricao,
                realizadaEm = transacao.realizadaEm.toString()
            )
        }
    }
}