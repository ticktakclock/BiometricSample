package com.github.ticktakclock.biometricsample

import android.content.Context
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.CancellationSignal
import android.widget.Toast

class BiometricPromptUseCase {
    fun start(context: Context) {
        val cancellationSignal = CancellationSignal()
        BiometricPrompt.Builder(context)
            .setTitle("生体認証")
            .setSubtitle("サブタイトルを添えて")
            .setDescription("詳細説明をここに記載します")
            .setNegativeButton("キャンセル", context.mainExecutor, DialogInterface.OnClickListener { dialog, which ->
                cancellationSignal.cancel()
            })
            .build()
            .authenticate(cancellationSignal, context.mainExecutor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context, "認証エラー:${errString}", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "認証失敗。", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                    super.onAuthenticationHelp(helpCode, helpString)
                    Toast.makeText(context, "ヘルプ呼び出し。", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)

                    Toast.makeText(context, "認証成功。", Toast.LENGTH_SHORT).show()
                }
            })
    }
}