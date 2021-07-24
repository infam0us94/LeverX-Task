package com.project.leverxtask.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.leverxtask.MyApp
import com.project.leverxtask.database.NewsDao
import com.project.leverxtask.repository.RemoteRepository
import com.project.leverxtask.repository.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListNewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = RemoteRepository.getInstance()

    @Inject
    lateinit var newsDao: NewsDao

    init {
        (application as MyApp).getAppComponent().inject(this)
    }

    private val listNews: MutableLiveData<List<News>> = MutableLiveData()

    fun getListNews(): MutableLiveData<List<News>> {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.getNewsList().isNullOrEmpty()) {
                getAllNews()
            } else {
                deleteNews()
                listNews.postValue(repo.getNewsList())
                insertNews(repo.getNewsList())
            }
        }
        return listNews
    }

    private fun getAllNews() {
        val allNews = newsDao.getAllNewsFromDB()
        listNews.postValue(allNews)
    }

    private fun insertNews(newsEntity: List<News>) {
        newsDao.insertNews(newsEntity)
    }

    private fun deleteNews() {
        newsDao.deleteNews()
    }
}