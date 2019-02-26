package com.zimplifica.awsplatform.useCases

import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.SignInState
import com.amazonaws.mobile.client.results.SignUpResult
import com.zimplifica.awsplatform.Utils.AWSErrorDecoder
import com.zimplifica.domain.entities.GenericResponse
import com.zimplifica.domain.entities.Result
import com.zimplifica.domain.entities.SignInError
import com.zimplifica.domain.entities.SignInResult
import com.zimplifica.domain.useCases.AuthenticationUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class AuthenticationUseCase : AuthenticationUseCase {

    override fun signIn(username: String, password: String): Observable<GenericResponse<SignInResult, SignInError>> {

        val single = Single.create<GenericResponse<SignInResult, SignInError>>  create@{single ->
            val client = AWSMobileClient.getInstance()
            client.signIn(username,password,  null, object : Callback<com.amazonaws.mobile.client.results.SignInResult>{
                override fun onResult(result: com.amazonaws.mobile.client.results.SignInResult?) {
                    val state = result?.signInState ?: SignInState.UNKNOWN
                    val signInResult = SignInResult(state = state.toString())
                    val response = GenericResponse<SignInResult,SignInError>(signInResult, true, null)
                    single.onSuccess(response)
                }
                override fun onError(e: Exception?) {
                    val error = AWSErrorDecoder.decodeSignInErrorError(e)
                    val response = GenericResponse<SignInResult,SignInError>(null,false, error)
                    single.onSuccess(response)
                }
            })
        }
        return single.toObservable()

    }
}