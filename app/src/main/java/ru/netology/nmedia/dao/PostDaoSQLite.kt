package ru.netology.nmedia.dao

import ru.netology.nmedia.dto.Post

interface PostDaoSQLite {
    fun getAll(): List<Post>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post): Post
}