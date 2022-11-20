package ru.netology.nmedia.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            authorTextView.text = post.author
            publishedTextView.text = post.published
            contentTextView.text = post.content
            likeButton.text = countToString(post.likeCount)
            shareButton.text = countToString(post.shareCount)
            viewsButton.text = countToString(post.viewCount)
            likeButton.isChecked = post.likedByMe
            if (post.video == null) videoGroup.visibility = View.GONE else videoGroup.visibility = View.VISIBLE

            likeButton.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            shareButton.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            optionsMenuButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }

            videoButton.setOnClickListener {
                post.video?.let { url -> onInteractionListener.onPlay(url) }
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