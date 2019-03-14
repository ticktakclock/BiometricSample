package com.github.ticktakclock.biometricsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authButton = findViewById<Button>(R.id.activity_main_auth_btn)
        authButton.setOnClickListener { v -> startAuthentication() }

        val androidxAuthButton = findViewById<Button>(R.id.activity_main_auth_androidx_btn)
        androidxAuthButton.setOnClickListener { v -> startAuthenticationWithAndroidX() }
    }
    private fun startAuthentication() {
        try {
            BiometricPromptUseCase().start(this)
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Error) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startAuthenticationWithAndroidX() {
        try{
            AndroidXBiometricPromptUseCase().start(this)
        } catch(e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Error) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }

}
