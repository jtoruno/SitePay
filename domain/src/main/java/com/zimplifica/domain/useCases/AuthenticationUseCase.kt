package com.zimplifica.domain.useCases

import com.zimplifica.domain.entities.GenericResponse
import io.reactivex.Observable

interface AuthenticationUseCase {
    fun signIn()
    fun signUp(username : String, password : String) : Observable<GenericResponse>
}