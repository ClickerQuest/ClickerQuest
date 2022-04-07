package com.example.clickerquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.parse.ParseUser

class StartMenu : AppCompatActivity() {

    private lateinit var logoutBtn:Button
    private lateinit var start:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_menu)

        logoutBtn = findViewById(R.id.logout)
        start = findViewById(R.id.play)

        logoutBtn.setOnClickListener {
            ParseUser.logOut()
            goToLoginActivity()
        }

        start.setOnClickListener {
            goToMainActivity()
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this@StartMenu, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    open fun goToMainActivity() {
        val intent = Intent(this@StartMenu, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}