package com.project.leverxtask.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_entity")
data class News(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String?,
    @ColumnInfo(name = "link")
    val link: String
)