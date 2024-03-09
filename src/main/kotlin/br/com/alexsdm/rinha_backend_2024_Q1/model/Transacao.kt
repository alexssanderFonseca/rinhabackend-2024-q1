package br.com.alexsdm.rinha_backend_2024_Q1.model

import jakarta.persistence.*
import java.math.BigInteger
import java.time.Instant
import java.util.*


@Entity
class Transacao(
    val valor: Int,
    val tipo: String,
    val descricao: String,
    val realizadaEm: Instant,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: Cliente,
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transacao_seq_gen")
    @SequenceGenerator(name = "transacao_seq_gen", sequenceName = "transacao_id_seq")
    var id: BigInteger? = null,
    val external_id: UUID = UUID.randomUUID(),
) {

}
