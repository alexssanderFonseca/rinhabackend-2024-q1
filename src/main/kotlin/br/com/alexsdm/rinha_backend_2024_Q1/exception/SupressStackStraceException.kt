package br.com.alexsdm.rinha_backend_2024_Q1.exception

open class SupressStackStraceException(mensagem: String) : RuntimeException(mensagem, null, true, false) {
}