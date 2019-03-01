package com.zimplifica.sitepay

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.zimplifica.sitepay.Scenes.SignIn.SignInViewModel
import com.zimplifica.sitepay.extensions.onChange
import com.zimplifica.sitepay.viewModels.SignUpViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class SignInActivity : AppCompatActivity() {

    lateinit var userNameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var passwordLayout : TextInputLayout
    lateinit var nextBtn : Button
    lateinit var MVM : SignInViewModel.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        this.supportActionBar?.title = "Iniciar Sesión"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        userNameInput = findViewById(R.id.user_name_main2)
        nextBtn = findViewById(R.id.button)
        passwordInput = findViewById(R.id.password_main2)
        passwordLayout = findViewById(R.id.textInputLayout2)
        val toogle = passwordLayout.isPasswordVisibilityToggleEnabled

        val usecase = Application.getInstance(this).awsProvider.makeAuthenticationUseCase()
        MVM = SignInViewModel.ViewModel(usecase)


        userNameInput.onChange { MVM.inputs.username(it) }
        passwordInput.onChange { MVM.inputs.password(it) }
        MVM.outputs.signInButtonIsEnabled()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({setLoginButtonEnabled(it)})

        nextBtn.setOnClickListener {
            closeKeyboard()
            MVM.inputs.signInPress() }

        errorMessages()

    }

    private fun setLoginButtonEnabled(enabled : Boolean){
        nextBtn.isEnabled = enabled
    }

    private fun errorMessages() =
        MVM.outputs.errorMessage()
            .subscribe {
                if (it!=""){
                    Log.e("SignInError", it)
                    showSnackBar(it)
                }
            }
    fun showSnackBar(message : String){
        val view = findViewById<View>(android.R.id.content)
        val snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snack.view.setOnClickListener {
            snack.dismiss()
        }
        snack.show()
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        finish()
    }
}
