package com.github.ticktakclock.biometricsample

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

class AndroidXBiometricPromptUseCase {
    fun start(activity: FragmentActivity) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("AndroidXによる生体認証")
            .setSubtitle("サブタイトルを添えて")
            .setDescription("詳細説明をここに記載します")
            .setNegativeButtonText("Negativeボタン")
            .build()
        // context.mainExecutorはAPI28からなので自分でmainExecutorを作成
        BiometricPrompt(activity, mainExecutor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(activity, "認証エラー:${errString}", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(activity, "認証失敗", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(activity, "認証成功", Toast.LENGTH_SHORT).show()
            }
        }).authenticate(promptInfo)
    }

    private val mainExecutor = Executor { x -> Handler(Looper.getMainLooper()).post(x)}
}