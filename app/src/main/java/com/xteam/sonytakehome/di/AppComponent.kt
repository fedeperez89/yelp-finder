package com.xteam.sonytakehome.di

import android.content.Context
import com.xteam.sonytakehome.TakeHomeApplication
import com.xteam.sonytakehome.ui.search.SearchModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        SearchModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<TakeHomeApplication> {


    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }


}