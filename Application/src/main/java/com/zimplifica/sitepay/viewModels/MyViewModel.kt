package com.zimplifica.sitepay.viewModels

import android.os.Environment
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.subjects.PublishSubject


interface SignUpViewModel {

    interface Inputs {

        fun usernameChanged()
        fun passwordChanged()
        fun togglePasswordVisibility()
        fun signUpButtonPressed()

    }

    interface Outputs {
        var errorMessage : PublishSubject<String>
        var passwordVisibility : PublishSubject<Boolean>
        var signUpButtonEnabled : PublishSubject<Boolean>

    }

    class ViewModel () : Inputs, Outputs {
        override fun usernameChanged() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun passwordChanged() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun togglePasswordVisibility() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun signUpButtonPressed() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override var errorMessage: PublishSubject<String>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            set(value) {}
        override var passwordVisibility: PublishSubject<Boolean>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            set(value) {}
        override var signUpButtonEnabled: PublishSubject<Boolean>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            set(value) {}


        val inputs: Inputs = this
        val outputs: Outputs = this

        init {

        }
    }
}

