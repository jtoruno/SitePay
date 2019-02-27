package com.zimplifica.sitepay.mocks

import com.zimplifica.domain.entities.GenericResponse
import com.zimplifica.domain.entities.SignInError
import com.zimplifica.domain.entities.SignInResult
import com.zimplifica.domain.useCases.AuthenticationUseCase
import io.reactivex.Observable

class AuthenticationUseCase : AuthenticationUseCase {
    override fun signIn(username: String, password: String): Observable<GenericResponse<SignInResult, SignInError>> {
        return Observable.create<GenericResponse<SignInResult,SignInError>>create@ { observer ->
            if(username == "Jtoru" && password == "123"){
                val result = SignInResult("successful")
                val response = GenericResponse<SignInResult,SignInError>(result,true,null)
                observer.onNext(response)
                observer.onComplete()
            }
            else{
                val response = GenericResponse<SignInResult, SignInError>(null,false, SignInError.userNotConfirmed)
                observer.onNext(response)
                observer.onComplete()
            }
        }

    }
}