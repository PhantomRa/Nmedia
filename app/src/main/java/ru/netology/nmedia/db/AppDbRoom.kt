package ru.netology.nmedia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.nmedia.dao.PostDaoRoom
import ru.netology.nmedia.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDbRoom : RoomDatabase() {
    abstract fun getPostDao(): PostDaoRoom

    companion object {
        @Volatile
        private var instance: AppDbRoom? = null

        fun getInstance(context: Context): AppDbRoom {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDbRoom::class.java, "app.db")
                .allowMainThreadQueries()
                .build()
    }
}