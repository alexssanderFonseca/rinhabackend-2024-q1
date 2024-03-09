package br.com.alexsdm.rinha_backend_2024_Q1.repository

import br.com.alexsdm.rinha_backend_2024_Q1.model.Cliente
import br.com.alexsdm.rinha_backend_2024_Q1.model.Transacao
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransacaoRepository : JpaRepository<Transacao, UUID> {

    fun findTop10ByClienteOrderByRealizadaEmDesc(cliente: Cliente): List<Transacao>

}
