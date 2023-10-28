package com.example.design

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.design.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var email: EditText
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var age: EditText
    private var info: Info? = null

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
                Toast.makeText(this, "User Email Error!", Toast.LENGTH_LONG).show()
            } else if (!isValidUserName(userName.text.toString())) {
                Toast.makeText(this, "user name Error", Toast.LENGTH_LONG).show()
            }else if (!isStrongPassword(password.text.toString())) {
                Toast.makeText(this, "Password Error", Toast.LENGTH_LONG).show()
            }else if (firstName.text.isBlank() || lastName.text.isBlank() || age.text.isBlank()) {
                Toast.makeText(this, "first name Error", Toast.LENGTH_LONG).show()
            }else {
                info?.email = email.text.toString()
                info?.userName = userName.text.toString()
                info?.password = password.text.toString()
                info?.firstName = firstName.text.toString()
                info?.lastName = lastName.text.toString()
                info?.age = age.text.toString().toInt()

                binding.constraintInputs.isVisible = false



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

data class Info(
    var email: String,
    var userName: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var age: Int,
)