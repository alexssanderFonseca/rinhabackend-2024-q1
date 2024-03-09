package br.com.alexsdm.rinha_backend_2024_Q1.repository

import br.com.alexsdm.rinha_backend_2024_Q1.model.Cliente
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ClienteRepository : JpaRepository<Cliente, Int> {

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun buscaClienteParaAtualizacaoSaldo(id: Int): Optional<Cliente>
}