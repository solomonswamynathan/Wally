package com.example.wally

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val registerButton = findViewById<ImageView>(R.id.imageView5)

        //onclick of Register button
        registerButton.setOnClickListener{
//            getting values from view using findViewByID
            val name = findViewById<EditText>(R.id.editText_name)
            val email = findViewById<EditText>(R.id.editTextEmail)
            val password = findViewById<EditText>(R.id.editTextPassword)
            val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)

            //comparing password and confirmPassword
            if(password.text.toString() != confirmPassword.text.toString()){
                confirmPassword.error = "Password mismatch"
            }

            //saving multiple data in sharedPref
            editor.apply{
                putString("name", name.text.toString())
                putString("email", email.text.toString())
                putString("password", password.text.toString())
                apply()
            }

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}