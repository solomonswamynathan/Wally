package com.example.wally

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.wally.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val registerButton = binding.imageView5

        //onclick of Register button
        registerButton.setOnClickListener{
//            getting values from view using findViewByID
            val name = binding.editTextName
            val email = binding.editTextEmail
            val password = binding.editTextPassword
            val confirmPassword = binding.editTextConfirmPassword

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