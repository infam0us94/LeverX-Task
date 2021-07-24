package com.project.leverxtask.di

import com.project.leverxtask.detail.DetailsNewsViewModel
import com.project.leverxtask.list.ListNewsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(listNewsViewModel: ListNewsViewModel)

    fun inject(detailsNewsViewModel: DetailsNewsViewModel)

}