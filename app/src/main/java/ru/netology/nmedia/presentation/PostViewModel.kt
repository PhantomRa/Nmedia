package ru.netology.nmedia.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl

private val empty = Post(
    id = 0,
    author = "",
    published = "",
    content = "",
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    //    Types: "memory", "sharedPref", "file", "db"
    private val saveType = "db"
    private val appDb = AppDb.getInstance(application)
    private var repository = when (saveType) {
        "db" -> PostRepositorySQLiteImpl(appDb.postDao)
        "sharedPref" -> PostRepositorySharedPrefsImpl(application)
        "file" -> PostRepositoryFileImpl(application)
        else -> PostRepositoryInMemoryImpl()
    }
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changePostContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
}