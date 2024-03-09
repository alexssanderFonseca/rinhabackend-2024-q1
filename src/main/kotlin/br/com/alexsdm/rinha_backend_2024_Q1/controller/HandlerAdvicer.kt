package br.com.alexsdm.rinha_backend_2024_Q1.controller

import br.com.alexsdm.rinha_backend_2024_Q1.exception.ClienteNaoEncontradoException
import br.com.alexsdm.rinha_backend_2024_Q1.exception.TransacaoInvalidaException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest


@RestControllerAdvice
class HandlerAdvicer {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleUserNotFoundException(
        exception: MethodArgumentNotValidException, request: WebRequest?
    ): ResponseEntity<Any> {
        return ResponseEntity.unprocessableEntity().build()
    }

    @ExceptionHandler(ClienteNaoEncontradoException::class)
    fun handleUserNotFoundException(
        exception: ClienteNaoEncontradoException, request: WebRequest?
    ): ResponseEntity<Any> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(TransacaoInvalidaException::class)
    fun handleUserNotFoundException(
        exception: TransacaoInvalidaException, request: WebRequest?
    ): ResponseEntity<Any> {
        return ResponseEntity.unprocessableEntity().build()
    }


}