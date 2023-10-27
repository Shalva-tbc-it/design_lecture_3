package com.example.design

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.design.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()


    }

    private fun onClick() {

        binding.btnSave.setOnClickListener {
            if (!isValidEmail(binding.edEmail.text)) {
                Toast.makeText(this, "User Email Error!", Toast.LENGTH_LONG).show()
            } else if (!isValidUserName(binding.edUserName.text.toString())) {
                Toast.makeText(this, "user name Error", Toast.LENGTH_LONG).show()
            }else if (!isStrongPassword(binding.edPass.text.toString())) {
                Toast.makeText(this, "Password Error", Toast.LENGTH_LONG).show()
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