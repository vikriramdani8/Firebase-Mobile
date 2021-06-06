package com.example.firebase_mobile

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.karn.notify.Notify
import java.util.*

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.e("receive", p0.notification?.title.toString())
        showNotification(p0.notification?.title.toString(), p0.notification?.body.toString())
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        var refreshToken = FirebaseInstanceId.getInstance().token
        Log.e("Test", refreshToken.toString())
    }

    fun showNotification(titles: String, message: String){
        val intent = Intent(this!!, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        intent.putExtra("message", message)
        Notify
            .with(this!!)
            .content {
                title = titles
                text = message
                startActivity(intent)
            }
    }
}
