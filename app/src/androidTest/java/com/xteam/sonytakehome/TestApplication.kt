package com.xteam.sonytakehome

import com.xteam.sonytakehome.di.DaggerTestAppComponent


class TestApplication : TakeHomeApplication() {

    override fun applicationInjector() = DaggerTestAppComponent.create()

}