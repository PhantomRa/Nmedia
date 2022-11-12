package ru.netology.nmedia.adapter

import android.widget.PopupMenu
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
            likeCount.text = countToString(post.likeCount)
            shareCount.text = countToString(post.shareCount)
            viewsCount.text = countToString(post.viewCount)
            likeButton.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            likeButton.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            shareButton.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            menuButton.setOnClickListener {
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