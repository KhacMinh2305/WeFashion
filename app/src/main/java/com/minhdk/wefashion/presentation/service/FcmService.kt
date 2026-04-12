package com.minhdk.wefashion.presentation.service

import com.google.firebase.messaging.FirebaseMessagingService

class FcmService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}