package com.zimplifica.sitepay.Scenes.SignIn

import com.zimplifica.domain.entities.GenericResponse
import com.zimplifica.domain.entities.SignInError
import com.zimplifica.domain.entities.SignInResult
import com.zimplifica.domain.useCases.AuthenticationUseCase
import com.zimplifica.sitepay.libs.rx.transformers.TakeWhenTransformer
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import com.zimplifica.sitepay.libs.rx.transformers.Transformers.*
import io.reactivex.ObservableSource
import java.util.*

interface SignInViewModel {

    interface Inputs {
        fun onCreate()
        fun username(username : String)
        fun password(password : String)
        fun signInPress()

    }

    interface Outputs {
        fun errorMessage() : Observable<String>
        fun signInStatus() : Observable<GenericResponse<SignInResult,SignInError>>
        fun signInButtonIsEnabled() : Observable<Boolean>
    }

    class ViewModel(val authenticationUseCase : AuthenticationUseCase?) : Inputs, Outputs {

        val inputs : Inputs = this
        val outputs : Outputs = this

        private val onCreateEvent = BehaviorSubject.create<Unit>()
        private val usernameEditTextChanged =  PublishSubject.create<String>()
        private val passwordEditTextChanged =  PublishSubject.create<String>()
        private val signInButtonPressed   = PublishSubject.create<Unit>()

        private val signInButtonIsEnabled = BehaviorSubject.create<Boolean>()


        override fun onCreate() {
            this.onCreateEvent.onNext(Unit)
        }

        override fun username(username: String) = this.usernameEditTextChanged.onNext(username)

        override fun password(password: String) = this.passwordEditTextChanged.onNext(password)


        override fun signInPress() {
            return this.signInButtonPressed.onNext(Unit)
        }

        init {

            val usernameAndPassword = Observable.combineLatest<String, String, Pair<String, String>>(
                usernameEditTextChanged,
                passwordEditTextChanged,
                BiFunction{
                        t1, t2 ->
                    Pair(t1, t2)
                })

            val isValid = usernameAndPassword
                .map { isValid(username = it.first, password = it.second)}


            isValid
                .subscribe(this.signInButtonIsEnabled)



            usernameAndPassword.subscribe{print("FormData: ${it.first} ${it.second}")}

            //val aux = takeWhen<Pair<String,String>,Unit>(signInButtonPressed) as ObservableSource<Unit>
            val signInStatusSignal = usernameAndPassword
                .compose{ takeWhen<Pair<String,String>,Unit>(signInButtonPressed).apply(it)}
                .switchMap { this.authenticationUseCase?.signIn(it.first, it.second) }
                .subscribe{
                    print("SignInStatus: ${it.status}")
                }


        }

        override fun errorMessage(): Observable<String> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun signInStatus(): Observable<GenericResponse<SignInResult, SignInError>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun signInButtonIsEnabled(): Observable<Boolean> = this.signInButtonIsEnabled

        private  fun isValid (username: String, password: String) = username.isNotEmpty() && password.isNotEmpty()
    }
}
