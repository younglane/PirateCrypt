package com.sigma.piratecrypt

import android.app.Application

class PirateCryptApplication: Application() {

    override fun onCreate(){
        super.onCreate()
        UserRepository.initialize(this)
    }

}