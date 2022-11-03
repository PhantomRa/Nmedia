package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.presentation.PostViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribe()
        setListeners()
    }

    private fun setListeners() {
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
        }
    }

    private fun subscribe() {
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = countToString(post.likeCount)
                shareCount.text = countToString(post.shareCount)
                viewsCount.text = countToString(post.viewCount)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
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