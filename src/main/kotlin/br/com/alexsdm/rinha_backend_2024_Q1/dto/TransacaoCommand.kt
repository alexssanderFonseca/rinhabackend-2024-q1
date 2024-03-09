package br.com.alexsdm.rinha_backend_2024_Q1.dto

import jakarta.validation.constraints.*
import java.math.BigDecimal

data class TransacaoCommand(
    @field:Positive
    @field:Digits(integer = 10, fraction = 0)
    val valor: BigDecimal?,
    @field:NotBlank
    @field:Pattern(regexp = "^[cd|CD]")
    val tipo: String?,
    @field:NotBlank
    @field:Size(min = 1, max = 10)
    val descricao: String?,
)



