package com.project.leverxtask.repository

import com.project.leverxtask.repository.model.DetailNews
import com.project.leverxtask.repository.model.Link
import com.project.leverxtask.repository.model.News

interface Repository {
    fun getNewsList(): MutableList<News>
    fun getDetailNews(linkUrl: String): DetailNews
    fun getLinks(): MutableList<Link>
}