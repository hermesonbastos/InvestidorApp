package com.example.investidorapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.investidorapp.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.investidorapp.R
import android.Manifest

class FirebaseMessagingService : FirebaseMessagingService() {

    // Metodo chamado quando o dispositivo recebe uma mensagem do Firebase
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Exibe a notificação somente se a mensagem tiver título e corpo
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    // Metodo chamado quando um novo token é gerado para o dispositivo
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Aqui você pode enviar o token para o backend, se necessário
        println("Token do dispositivo: $token")
    }

    // Exibe a notificação no dispositivo
    private fun showNotification(title: String?, message: String?) {
        val channelId = "investidor_notifications"
        val notificationId = (System.currentTimeMillis() % 10000).toInt()

        // Criação do canal de notificação (necessário no Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificações de Investimentos",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title ?: "Nova Notificação")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText(message ?: "Você recebeu uma nova mensagem.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        NotificationManagerCompat.from(this).notify(notificationId, notification)
    }
}
