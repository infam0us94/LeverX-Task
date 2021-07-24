package com.project.leverxtask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.leverxtask.repository.model.DetailNews
import com.project.leverxtask.repository.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_entity ORDER BY title DESC")
    fun getAllNewsFromDB(): List<News>

    @Query("SELECT * FROM detail_news_entity ORDER BY main_title DESC")
    fun getDetailNewsFromDB(): DetailNews

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsEntity: List<News>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailNews(newsEntity: DetailNews)

    @Query("DELETE FROM news_entity")
    fun deleteNews()

}