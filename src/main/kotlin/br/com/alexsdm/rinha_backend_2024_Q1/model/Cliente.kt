package br.com.alexsdm.rinha_backend_2024_Q1.model

import br.com.alexsdm.rinha_backend_2024_Q1.exception.TransacaoInvalidaException
import jakarta.persistence.*
import kotlin.math.abs

@Entity
class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq_gen")
    @SequenceGenerator(name = "cliente_seq_gen", sequenceName = "cliente_id_seq")
    val id: Int,
    val limite: Int,
    var saldo: Int
) {
    fun creditar(credito: Int) {
        this.saldo = this.saldo.plus(credito)
    }

    fun debitar(debito: Int) {
        val saldoAposDebito = this.saldo.minus(debito)
        if (saldoAposDebito < 0 && abs(saldoAposDebito) > limite) {
            throw TransacaoInvalidaException()
        }
        this.saldo = saldoAposDebito

    }
}