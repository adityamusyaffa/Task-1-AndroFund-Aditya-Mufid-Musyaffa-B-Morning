package com.example.rolepicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.username)
        etPassword = findViewById(R.id.password)

        val loginBtn: Button = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginBtn -> {
                val bundle = Bundle()
                bundle.putString("username", etUsername.text.toString())
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}