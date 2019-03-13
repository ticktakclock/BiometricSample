package com.github.ticktakclock.biometricsample

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.activity_main_auth_btn)
        button.setOnClickListener { v -> startAuthentication() }
    }
    private fun startAuthentication() {
        val cancellationSignal = CancellationSignal()
        BiometricPrompt.Builder(this)
            .setTitle("生体認証")
            .setDescription("詳細説明をここに記載します")
            .setNegativeButton("キャンセル", mainExecutor, DialogInterface.OnClickListener { dialog, which ->
                cancellationSignal.cancel()
            })
            .build()
            .authenticate(cancellationSignal, mainExecutor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@MainActivity, "認証エラー:${errString}", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(this@MainActivity, "認証失敗。", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                    super.onAuthenticationHelp(helpCode, helpString)
                    Toast.makeText(this@MainActivity, "ヘルプ呼び出し。", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)

                    Toast.makeText(this@MainActivity, "認証成功。", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
