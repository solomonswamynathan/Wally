package com.example.wally

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)


        sharedPref.getString("name", null)

        val loginButton = findViewById<ImageView>(R.id.button_login)
        loginButton.setOnClickListener {
           var emailSharedPref = sharedPref.getString("email", null)
            var passwordSharedPref = sharedPref.getString("password",null)

            val emailText = findViewById<EditText>(R.id.editText_Email)
            val passwordText = findViewById<EditText>(R.id.editText_Password)

            if(emailSharedPref == emailText.text.toString() && passwordSharedPref == passwordText.text.toString()){
                print("User successfully logged In")
            }else{
                passwordText.error = "Incorrect Username or Password"
            }
        }

        //screen navigation using Intent
//        var textRegister = findViewById<TextView>(R.id.text_register)
//        textRegister.setOnClickListener {
//            val intent= Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
    }
}