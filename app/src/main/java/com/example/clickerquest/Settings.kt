package com.example.clickerquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.parse.ParseUser

class Settings : AppCompatActivity() {

    private lateinit var logoutBtn:Button
    private lateinit var close:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        logoutBtn = findViewById(R.id.logout_button)
        close = findViewById(R.id.close_settings)

        logoutBtn.setOnClickListener {
            ParseUser.logOut()
            goToLoginActivity()
        }

        close.setOnClickListener {
            goToMainActivity()
        }

    }

    private fun goToLoginActivity() {
        val intent = Intent(this@Settings, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    open fun goToMainActivity() {
        val intent = Intent(this@Settings, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}