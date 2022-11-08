package ru.netology.nmedia.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentPost: Post

    init {
        binding.like.setOnClickListener(::onLikeClick)
        binding.share.setOnClickListener(::onShareClick)
    }
    private fun onLikeClick(v: View) {
        onLikeListener(currentPost)
    }
    private fun onShareClick(v: View) {
        onShareListener(currentPost)
    }

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = countToString(post.likeCount)
            shareCount.text = countToString(post.shareCount)
            viewsCount.text = countToString(post.viewCount)
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            like.setOnClickListener {
                onLikeListener(post)
            }
            share.setOnClickListener {
                onShareListener(post)
            }
        }
    }

    private fun countToString(count: Long): String = when (count) {
        in Long.MIN_VALUE until 0 -> "${0}"
        in 0 until 1_000 -> "$count"
        in 1_000 until 10_000 -> "%.1fK".format((count / 100) / 10f)
        in 10_000 until 1_000_000 -> "%dK".format(count / 1_000)
        in 1_000_000 until 10_000_000 -> "%.1fM".format((count / 100_000) / 10f)
        in 10_000_000 until 1_000_000_000 -> "%dM".format(count / 1_000_000)
        else -> "INF"
    }
}