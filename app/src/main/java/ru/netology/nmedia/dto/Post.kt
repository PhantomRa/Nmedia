package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean = false,
    var likeCount: Long = 0,
    var shareCount: Long = 0,
    var viewCount: Long = 0,
)
