package com.project.leverxtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.leverxtask.repository.model.DetailNews
import com.project.leverxtask.repository.model.News

@Database(entities = [(News::class), (DetailNews::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        private var db_instance: AppDatabase? = null

//        private val migration_1_2:Migration = object: Migration(1,2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//             database.execSQL("")
//            }
//        }

        fun getAppDatabaseInstance(context: Context): AppDatabase {
            if (db_instance == null) {
                db_instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "app_db"
                )
                    .allowMainThreadQueries()
//                    .addMigrations(migration_1_2)
                    .build()
            }
            return db_instance!!
        }
    }
}