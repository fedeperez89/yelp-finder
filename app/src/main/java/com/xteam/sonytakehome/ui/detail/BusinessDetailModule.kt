package com.xteam.sonytakehome.ui.detail

import androidx.lifecycle.ViewModel
import com.xteam.sonytakehome.di.ViewModelBuilder
import com.xteam.sonytakehome.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BusinessDetailModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun businessDetailFragment(): BusinessDetailFragment


    @Binds
    @IntoMap
    @ViewModelKey(BusinessDetailViewModel::class)
    abstract fun bindViewModel(viewmodel: BusinessDetailViewModel): ViewModel
}