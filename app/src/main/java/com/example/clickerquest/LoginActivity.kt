package com.example.clickerquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (ParseUser.getCurrentUser() != null) {
            goToStart()
        }

        findViewById<Button>(R.id.loginbutton).setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            loginUser(username, password)
        }

        findViewById<Button>(R.id.signupbutton).setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            signUpUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i("Login", "Successfully logged in")
                Toast.makeText(this,"Welcome $username!", Toast.LENGTH_SHORT).show()
                goToStart()
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Incorrect username or password", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun signUpUser(username:String, password:String) {
        val user = ParseUser()

        user.setUsername(username)
        user.setPassword(password)
        user.put("level", 1)
        user.put("attack_power", 1)
        user.put("stage_progress", 1)
        user.put("stage_cycle", 1)
        user.put("gold", 0)

        user.signUpInBackground { e ->
            if (e == null) {
                Log.i("Signup", "Successfully signed up " + user.username)
                Toast.makeText(this,"Welcome $username!", Toast.LENGTH_SHORT).show()
                goToStart()
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Username already taken", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToStart() {
        val intent = Intent(this@LoginActivity, StartMenu::class.java)
        startActivity(intent)
        finish()
    }
}