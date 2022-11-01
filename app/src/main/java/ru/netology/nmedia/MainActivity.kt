package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.nextDown
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            1,
            "Нетология. Университет интернет-профессий будущего",
            "21 мая в 18:36",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов " +
                    "по онлайн-маркетингу. Затем повились курсы по дизайну, разработке, аналитике и управлению. " +
                    "Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но " +
                    "самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет " +
                    "хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и " +
                    "начать цепочку перемен -> \nhttp://netolo.gy/fyb",
            false,
            999,
            999999,
            1199999
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = countToString(post.likes)
            shareCount.text = countToString(post.shareCount)
            viewsCount.text = countToString(post.views)
            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_liked_24)
            }
            like.setOnClickListener {
                if (post.likedByMe) post.likes-- else post.likes++
                post.likedByMe = !post.likedByMe
                like.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
                likeCount.text = countToString(post.likes)
            }
            share.setOnClickListener {
                post.shareCount++
                shareCount.text = countToString(post.shareCount)
            }
            views.setOnClickListener {
                post.views++
                viewsCount.text = countToString(post.views)
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