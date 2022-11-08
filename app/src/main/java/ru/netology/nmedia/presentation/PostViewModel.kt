package ru.netology.nmedia.presentation

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository

class PostViewModel: ViewModel() {
    private var repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
}