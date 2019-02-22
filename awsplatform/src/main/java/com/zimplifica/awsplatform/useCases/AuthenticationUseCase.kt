package com.zimplifica.awsplatform.useCases

import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.SignUpResult
import com.zimplifica.domain.entities.GenericResponse
import com.zimplifica.domain.entities.Result
import com.zimplifica.domain.useCases.AuthenticationUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class AuthenticationUseCase : AuthenticationUseCase {
    override fun signIn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signUp(username: String, password: String): Observable<GenericResponse> {

        val single = Single.create<GenericResponse>  create@{
            val client = AWSMobileClient.getInstance()
            client.signUp(username,password, hashMapOf<String,String>(), null, object : Callback<SignUpResult>{
                override fun onResult(result: SignUpResult?) {
                    val genericResult = GenericResponse(Result(true),true,null)
                    it.onSuccess(genericResult)
                }
                override fun onError(e: Exception?) {
                    val error = Error(e)
                    it.onError(error)
                }
            })
        }
        return single.toObservable()
    }
}