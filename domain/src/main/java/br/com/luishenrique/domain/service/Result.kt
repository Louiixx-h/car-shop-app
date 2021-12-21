package br.com.luishenrique.domain.service

import java.lang.Exception

sealed class Result<out R> {
    data class Success<out S>(val data: S?) : Result<S?>()
    data class Error(val exception: Exception) : Result<Nothing>()
}