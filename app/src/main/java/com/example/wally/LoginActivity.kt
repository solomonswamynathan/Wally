package com.example.wally

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.wally.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)


        sharedPref.getString("name", null)

        val loginButton = binding.buttonLogin
        loginButton.setOnClickListener {
           val emailSharedPref = sharedPref.getString("email", null)
            val passwordSharedPref = sharedPref.getString("password",null)

            val emailText = binding.editTextEmail
            val passwordText = binding.editTextPassword

            if(emailSharedPref == emailText.text.toString() && passwordSharedPref == passwordText.text.toString()){
                Log.i("Login: ","User successfully logged In")
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }else{
                passwordText.error = "Incorrect Username or Password"
            }
        }

    }
}