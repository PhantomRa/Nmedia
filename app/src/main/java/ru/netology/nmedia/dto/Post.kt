package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean = false,
    var likes: Long = 0,
    var shareCount: Long = 0,
    var views: Long = 0,
)
