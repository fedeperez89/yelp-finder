package com.xteam.sonytakehome.ui.detail

import com.xteam.sonytakehome.di.ViewModelBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BusinessDetailModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun businessDetailFragment(): BusinessDetailFragment
}