package com.example.registeration

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registeration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
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

        binding.submitBtn.setOnClickListener {
            if (validateFields()) {
                binding.fullName.text.clear()
                binding.phoneNumber.text.clear()
                binding.email.text.clear()
                binding.password.text.clear()
                binding.conformPassword.text.clear()
                Toast.makeText(applicationContext, "Sign Up Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Validation failed!", Toast.LENGTH_SHORT).show();

            }
        }
        binding.confirmPassword.setOnClickListener(this)
    }

    private fun validateFields(): Boolean {
        val username = binding.fullName.text.toString().trim()
        val phone = binding.phoneNumber.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()
        val confirmPassword = binding.conformPassword.text.toString().trim()

        val phonePattern = "^[+]?[0-9]{8,15}$"
        val isValidPhone = phone.matches(phonePattern.toRegex())
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val isValidMail = email.matches(emailPattern.toRegex())

        if (username.isEmpty()) {
            binding.fullName.error = " Enter Your Name"
            return false
        }

        if (!isValidPhone) {
            binding.phoneNumber.error = "Enter a valid number"
            return false
        }
        if (!isValidMail) {
            binding.email.error = "Invalid Email address"
            return false
        }

        if (password.isEmpty()) {
            binding.password.error = "Password cannot be empty"
            return false
        } else if (password != confirmPassword) {
            binding.conformPassword.error = "Password Mismatch"
            return false
        }

        return true
    }

    private fun showHidePassWord(v: View?) {
        if (v?.id == R.id.confirmPassword) {
            if (binding.conformPassword.transformationMethod
                    .equals(PasswordTransformationMethod.getInstance())
            ) {
                binding.confirmPassword.setImageResource(R.drawable.ic_password_eye)
                binding.conformPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.confirmPassword.setImageResource(R.drawable.ic_hide_eyes)
                binding.conformPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.confirmPassword -> {
                showHidePassWord(v)
            }
        }
    }
}