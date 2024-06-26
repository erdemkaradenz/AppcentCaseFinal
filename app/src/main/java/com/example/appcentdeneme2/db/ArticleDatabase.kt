package com.example.appcentdeneme2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.appcentdeneme2.models.Article

@Database(
    entities = [Article::class],
    version = 2
)

@TypeConverters(Converters::class)

abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDAO(): ArticleDAO
    companion object{
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}