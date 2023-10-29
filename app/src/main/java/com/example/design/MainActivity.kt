package com.example.design

import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.design.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var email: EditText
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var age: EditText
    private var info: Info? = null
    private var passwordVisibility = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = binding.edEmail
        userName = binding.edUserName
        password = binding.edPass
        firstName = binding.edFirstName
        lastName = binding.edLastName
        age = binding.edAge

        onClick()

    }

    private fun onClick() {

        binding.btnSave.setOnClickListener {
            if (!isValidEmail(email.text)) {
                Toast.makeText(
                    this, "Enter your email correctly", Toast.LENGTH_LONG).show()
            } else if (!isValidUserName(userName.text.toString())) {
                Toast.makeText(
                    this, "Username must be at least 10 characters long", Toast.LENGTH_LONG).show()
            } else if (!isStrongPassword(password.text.toString())) {
                Toast.makeText(
                    this, "Invalid password format. (Xxxx123@)", Toast.LENGTH_LONG).show()
            } else if (firstName.text.isBlank() || lastName.text.isBlank() || age.text.isBlank()) {
                Toast.makeText(
                    this, "Please fill in all fields", Toast.LENGTH_LONG).show()
            } else {

                info = Info(
                    email = email.text.toString(),
                    userName = userName.text.toString(),
                    password = password.text.toString(),
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    age = age.text.toString()
                )

                binding.constraintInputs.isVisible = false
                binding.constraintInfo.isVisible = true

                binding.tvEmailValue.text = info?.email
                binding.tvUserNameValue.text = info?.userName
                binding.tvPassValue.text = info?.password
                binding.tvFullNameValue.text = info?.firstName + " " + info?.lastName
                binding.tvAgeValue.text = info?.age

            }
        }

        binding.btnClear.setOnLongClickListener {
            email.text.clear()
            userName.text.clear()
            password.text.clear()
            firstName.text.clear()
            lastName.text.clear()
            age.text.clear()
            true
        }

        binding.btnAgain.setOnClickListener {

            binding.constraintInputs.isVisible = true
            binding.constraintInfo.isVisible = false

            email.text.clear()
            userName.text.clear()
            password.text.clear()
            firstName.text.clear()
            lastName.text.clear()
            age.text.clear()

        }

        binding.imgBtnShowPass.setOnClickListener {
            if (!passwordVisibility) {
                binding.edPass.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordVisibility = true
            } else {
                binding.edPass.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passwordVisibility = false
            }


        }

    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun isValidUserName(target: String): Boolean {
        val clearSpace = removeSpaces(target)
        val userNameArray =
            clearSpace.toCharArray()
        return !TextUtils.isEmpty(target) && userNameArray.size >= 10
    }

    private fun removeSpaces(input: String): String {
        return input.replace(" ", "")
    }


    private fun isStrongPassword(password: String): Boolean {
        val pattern = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}\$")
        return pattern.matches(password)
    }


}

