package ru.netology.nmedia.dto

import android.widget.VideoView

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val video: String? = null,
    val likedByMe: Boolean = false,
    var likeCount: Long = 0,
    var shareCount: Long = 0,
    var viewCount: Long = 0,
)
