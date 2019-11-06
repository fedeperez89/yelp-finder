package com.xteam.sonytakehome.di

import com.xteam.sonytakehome.di.AppComponent
import com.xteam.sonytakehome.di.TestApplicationModule
import com.xteam.sonytakehome.ui.detail.BusinessDetailModule
import com.xteam.sonytakehome.ui.search.SearchModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [TestApplicationModule::class,
        SearchModule::class,
        BusinessDetailModule::class,
        AndroidSupportInjectionModule::class]
)
interface TestAppComponent : AppComponent {

}