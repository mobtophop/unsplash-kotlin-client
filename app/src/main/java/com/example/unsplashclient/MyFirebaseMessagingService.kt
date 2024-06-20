package com.example.unsplashclient
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @Override
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}