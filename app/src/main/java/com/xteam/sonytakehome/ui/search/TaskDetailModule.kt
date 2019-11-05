package com.xteam.sonytakehome.ui.search

import androidx.lifecycle.ViewModel
import com.xteam.sonytakehome.di.ViewModelBuilder
import com.xteam.sonytakehome.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun taskDetailFragment(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindViewModel(viewmodel: SearchViewModel): ViewModel
}