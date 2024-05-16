package com.example.kinokz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        usernameEditText = findViewById(R.id.editText_username)
        emailEditText = findViewById(R.id.editText_email)
        passwordEditText = findViewById(R.id.editText_password)
        signUpButton = findViewById(R.id.button_registration)

        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                saveUser(username, email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUser(username: String, email: String, password: String) {
        val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("Username", username)
            putString("Email", email)
            putString("Password", password)
            apply()
        }
        Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()

        // Переход на MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Закрываем текущую активность после перехода
    }

}
