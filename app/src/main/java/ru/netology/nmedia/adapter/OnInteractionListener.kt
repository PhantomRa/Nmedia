package ru.netology.nmedia.adapter

import ru.netology.nmedia.dto.Post

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onPlay(uri: String) {}
    fun onPostClick(id: Long) {}
}