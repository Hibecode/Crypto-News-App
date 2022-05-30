package com.example.crypto_news_app.db

import android.content.Context
import androidx.room.*
import com.example.crypto_news_app.models.Article

@Database( entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDB: RoomDatabase() {

    abstract fun articleDao(): ArticleDao


    companion object{
        //using @Volatile make writes to this field visible to other threads
        @Volatile
        private var instance: ArticleDB? = null
        private val LOCK = Any()


        //invoke() operator is used to allows instances of the class to be called
        //without a method name
        operator fun invoke(context: Context) =
            //checks if instance is null, if it isn't,
            // it uses a synchronized block; where it checks if instance is still null, then,
            //it creates the database and assigns the created db to the instance variable
            instance ?: synchronized(LOCK) {
                //using synchronized prevents other threads from accessing this block
                //LOCK serves as a lock to the block
                instance ?: createDatabase(context).also { instance = it }
            }



        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDB::class.java, "article_db"
            ).build()


    }
}