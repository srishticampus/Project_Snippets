package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginButton.setOnClickListener {
            if (validateFields()) {
                binding.phNoEditText.text.clear()
                binding.passdEditText.text.clear()
                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateFields(): Boolean {
        val username = binding.phNoEditText.text.toString().trim()
        val password = binding.passdEditText.text.toString().trim()
        val phonePattern = "^[+]?[0-9]{8,15}$"
        val isValidPhone = username.matches(phonePattern.toRegex())

        if (!isValidPhone) {
            binding.phNoEditText.error = "Enter a valid number"
            return false
        }

        if (password.isEmpty()) {
            binding.passdEditText.error = "Password cannot be empty"
            return false
        }

        return true
    }
}