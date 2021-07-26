package com.project.leverxtask.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.leverxtask.MyApp
import com.project.leverxtask.database.NewsDao
import com.project.leverxtask.repository.RemoteRepository
import com.project.leverxtask.repository.model.Link
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsNewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = RemoteRepository.getInstance()

    private val detail: MutableLiveData<ArrayList<Link>> = MutableLiveData()

    @Inject
    lateinit var newsDao: NewsDao

    init {
        (application as MyApp).getAppComponent().inject(this)
    }

    fun getDetail(): MutableLiveData<ArrayList<Link>> {
        viewModelScope.launch(Dispatchers.IO) {
            detail.postValue(repo.getLinks())
        }
        return detail
    }
}