package com.project.leverxtask.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_news_entity")
data class DetailNews(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "main_title")
    var main_title: String = "",
    @ColumnInfo(name = "desc")
    var desc: String = "",
    @ColumnInfo(name = "image")
    var image: String = "",
    @ColumnInfo(name = "title")
    var title: String = ""
)