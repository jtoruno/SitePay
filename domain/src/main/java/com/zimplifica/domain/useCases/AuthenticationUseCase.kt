package com.zimplifica.domain.useCases

import com.zimplifica.domain.entities.GenericResponse
import com.zimplifica.domain.entities.SignInError
import com.zimplifica.domain.entities.SignInResult
import io.reactivex.Observable

interface AuthenticationUseCase {
    fun signIn(username : String, password : String) : Observable<GenericResponse<SignInResult, SignInError>>
    fun signUp(username : String, password: String) : Observable<GenericResponse<SignInResult, SignInError>>
}