package com.project.leverxtask.repository

import android.util.Log
import com.project.leverxtask.repository.model.DetailNews
import com.project.leverxtask.repository.model.News
import org.jsoup.Jsoup
import java.io.IOException

class RemoteRepository : Repository {

    companion object {
        private var repo: RemoteRepository? = null
        fun getInstance(): RemoteRepository {
            if (repo == null) {
                repo = RemoteRepository()
            }
            return repo!!
        }
    }

    override fun getNewsList(): ArrayList<News> {
        val listNews = ArrayList<News>()
        try {
            val url = "https://dev.by"
            val doc = Jsoup.connect(url).get()
            val element = doc.select("div[class=card card_media]")

            for (i in 0 until element.size) {
                val title = element.select("div[class=card__info]")
                    .select("div[class=card__body]")
                    .select("a")
                    .select("span")
                    .eq(i)
                    .text()

                val linkImage = doc.baseUri() + element.select("div[class=card__img-wrap]")
                    .select("img")
                    .eq(i)
                    .attr("src")

                val link =
                    doc.baseUri() +
                            element.select("div[class=card__info]")
                                .select("div[class=card__body]")
                                .select("a[class=card__link]")
                                .eq(i)
                                .attr("href")

                listNews.add(News(title, linkImage, link))
            }
        } catch (e: IOException) {
            Log.d("Exception", e.message.toString())
        }
        return listNews
    }

    override fun getDetailNews(linkUrl: String): DetailNews {
        val detail = DetailNews()
        try {
            val url = "https://dev.by/"
            val doc = Jsoup.connect(linkUrl).get()
            val doc2 = Jsoup.connect(url).get()
            val elements = doc.select("div[class=island__body-main]")

            val title = elements.select("article[class=article]").select("div[class=article__header]")
                .select("div[class=article__container]").select("p").text()

            val mainTitle =
                elements.select("article[class=article]").select("div[class=article__header]")
                    .select("div[class=article__container]").select("h1").text()

            val linkImage = doc2.baseUri() +
                    elements.select("article[class=article]")
                        .select("div[class=article__body]")
                        .select("figure[class=image]")
                        .select("img").attr("src")

            val desc = elements.select("article[class=article]").select("div[class=article__body]")
                .select("div[class=article__container]").select("p").text()

            detail.main_title = mainTitle.toString()
            detail.title = title.toString()
            detail.image = linkImage
            detail.desc = desc.toString()

        } catch (e: IOException) {
            Log.d("Exeption", e.message.toString())
        }
        return detail
    }
}