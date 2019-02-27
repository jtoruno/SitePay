package com.zimplifica.sitepay

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.zimplifica.sitepay.viewModels.SignUpViewModel
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class SignInActivity : AppCompatActivity() {

    lateinit var userNameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var passwordLayout : TextInputLayout
    lateinit var nextBtn : Button

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

        val MVM = SignUpViewModel.ViewModel()

        /*
        userNameInput.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })*/

        val userObs : Observable<Boolean> =
                RxTextView.textChanges(userNameInput)
                    .map {
                        user -> user.length >=5
                    }


        val passObs : Observable<Boolean> =
                RxTextView.textChanges(passwordInput)
                    .map {
                        t -> t.length >5
                    }
        passObs.distinctUntilChanged()
            .subscribe {
                t-> Log.d("RX password", t.toString())
            }

        val btnEnabled : Observable<Boolean> = Observable.combineLatest(userObs, passObs, BiFunction{u, p -> u && p})
        btnEnabled.distinctUntilChanged()
            .subscribe { enabled -> nextBtn.isEnabled = enabled }

        btnEnabled.distinctUntilChanged()
            .map {
                b-> if(b) R.color.colorPrimary else R.color.enabledColor
            }
            .subscribe {
                color -> nextBtn.backgroundTintList = ContextCompat.getColorStateList(this,color)
            }

        val btnSubs = RxView.clicks(nextBtn)
            .subscribe {
                Log.e("SignInBtn", "Clicked")
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
