package com.project.leverxtask.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.leverxtask.MyApp
import com.project.leverxtask.database.NewsDao
import com.project.leverxtask.repository.RemoteRepository
import com.project.leverxtask.repository.model.DetailNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsNewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = RemoteRepository.getInstance()

    private val detail: MutableLiveData<DetailNews> = MutableLiveData()

    @Inject
    lateinit var newsDao: NewsDao

    init {
        (application as MyApp).getAppComponent().inject(this)
    }

    fun getDetail(url: String): MutableLiveData<DetailNews> {
        viewModelScope.launch(Dispatchers.IO) {
       if (repo.getDetailNews(url) == null) {
                getDetailNews()
            } else {
                detail.postValue(repo.getDetailNews(url))
                insertDetailNews(repo.getDetailNews(url))
            }
        }
        return detail
    }

    private fun getDetailNews() {
        val detailNews = newsDao.getDetailNewsFromDB()
        detail.postValue(detailNews)
    }

    private fun insertDetailNews(newsEntity: DetailNews) {
        newsDao.insertDetailNews(newsEntity)
    }
}