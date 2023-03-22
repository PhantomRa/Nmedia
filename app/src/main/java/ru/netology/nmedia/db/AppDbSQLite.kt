package ru.netology.nmedia.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import ru.netology.nmedia.dao.PostDaoImpl
import ru.netology.nmedia.dao.PostDaoSQLite

class AppDbSQLite private constructor(db: SQLiteDatabase) {
    val postDao: PostDaoSQLite = PostDaoImpl(db)

    companion object {
        @Volatile
        private var instance: AppDbSQLite? = null

        fun getInstance(context: Context): AppDbSQLite {
            return instance ?: synchronized(this) {
                instance ?: AppDbSQLite(
                    buildDatabase(context, arrayOf(PostDaoImpl.DDL))
                ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, DDLs: Array<String>) =
            DbHelper(context, 1, "app.db", DDLs).writableDatabase
    }
}