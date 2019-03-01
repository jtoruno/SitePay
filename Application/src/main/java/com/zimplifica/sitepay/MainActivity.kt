package com.zimplifica.sitepay

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var signInBtn : Button
    lateinit var signUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signInBtn = findViewById(R.id.button3)
        signUpBtn = findViewById(R.id.button2)
        signInBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        signUpBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        //Application.getInstance(this).awsProvider.makeAuthenticationUseCase().signUp()
    }
}
