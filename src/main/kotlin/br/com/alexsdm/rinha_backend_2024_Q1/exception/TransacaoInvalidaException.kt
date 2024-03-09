package br.com.alexsdm.rinha_backend_2024_Q1.exception

class TransacaoInvalidaException(mensagem: String = "Transacao Invalida") :
    SupressStackStraceException(mensagem) {

}