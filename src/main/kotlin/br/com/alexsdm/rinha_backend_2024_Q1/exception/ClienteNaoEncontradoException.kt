package br.com.alexsdm.rinha_backend_2024_Q1.exception

class ClienteNaoEncontradoException(mensagem: String = "Cliente nao encontrado") :
    SupressStackStraceException(mensagem) {

}